package com.mehmetyurekli.Util;

import com.mehmetyurekli.Login.UserManager;
import com.mehmetyurekli.Models.User;
import com.mehmetyurekli.Mongo.MongoRepository;

import java.util.*;

public class SearchEngine { //does a similarity check using Jaro-Winkler algorithm.

    public static User[] searchUsers(String query){
        if(query == null){
            return null;
        }
        MongoRepository<User> repository = new MongoRepository<User>("Social", "Users", User.class);
        List<User> users = repository.getAll();
        query = query.toLowerCase();

        HashMap<User, Double> similarityMap = new HashMap<>();
        for(User u : users){ //search by name
            if(!u.isVisible()){
                if(!UserManager.getCurrentUser().isFriend(u.getId())){
                    continue;
                }
            }
            double nameSimilarity = findJaroDistance(query, u.getName().toLowerCase());
            double surnameSimilarity = findJaroDistance(query, u.getSurname().toLowerCase());
            double bothSimilarity = findJaroDistance(query, u.getName() + " " + u.getSurname().toLowerCase());
            double usernameSimilarity = findJaroDistance(query, u.getUsername().toLowerCase());

            double[] similarities = {nameSimilarity, surnameSimilarity, bothSimilarity, usernameSimilarity};
            double max = Double.MIN_VALUE;
            for (double similarity : similarities) {
                if (similarity > max) {
                    max = similarity;
                }
            }
            if (max > 0.75) { //its a match if the similarity is at least 75%
                similarityMap.put(u, max);
            }
        }

        if(similarityMap.isEmpty()){
            return null;
        }

        return similarityMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .toArray(User[]:: new);
    }

    private static double findJaroDistance(String str1, String str2) {
        if (str1.equals(str2)) {
            return 1.0;
        }

        int length1 = str1.length();
        int length2 = str2.length();

        int maxDistance = (int) (Math.floor(Math.max(length1, length2) / 2) - 1);

        int matches = 0;

        int[] hashStr1 = new int[length1];
        int[] hashStr2 = new int[length2];

        for (int i = 0; i < length1; i++) {
            for (int j = Math.max(0, i - maxDistance);
                 j < Math.min(length2, i + maxDistance + 1); j++)
                if (str1.charAt(i) == str2.charAt(j) && hashStr2[j] == 0) {
                    hashStr1[i] = 1;
                    hashStr2[j] = 1;
                    matches++;
                    break;
                }
        }
        if (matches == 0) {
            return 0.0;
        }

        double t = 0;
        int point = 0;

        for (int i = 0; i < length1; i++)
            if (hashStr1[i] == 1) {
                while (hashStr2[point] == 0)
                    point++;
                if (str1.charAt(i) != str2.charAt(point++))
                    t++;
            }

        t = t / 2;

        return (((double) matches) / ((double) length1) + ((double) matches) / ((double) length2) + ((double) matches - t)
                / ((double) matches)) / 3.0;
    }

}
