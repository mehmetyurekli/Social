<h1>Social</h1>

<p>
  <strong>Social</strong> is a Facebook-like application developed in Java using Swing. The project employs various design patterns, including Singleton, Observer, Builder, Repository, Composite, and Command. The user interface is crafted with Swing and MigLayout, while FlatLaf is used to enhance the look and feel. All data is securely stored in MongoDB, with passwords hashed using Argon2.
</p>

<h2>Features</h2>
<ul>
  <li>Create text based posts with titles and content.</li>
  <li>View friends' posts on the feed page.</li>
  <li>Update username, password, or profile visibility (allowing other users to find you via search).</li>
  <li>Add descriptions to your profile, with posts displayed in reverse chronological order.</li>
  <li>Instantly receive friend requests with a background thread checking for new requests every second.</li>
  <li>Search for users using the Jaro-Winkler algorithm, which matches users with a similarity score above 75%.</li>
  <li>Some complicated queries and operations are executed in separate threads to avoid freezing the GUI. It's my first time working with threads, so it might not be perfect, but it gets the job done.</li>
</ul>

<h2>Design Patterns Used</h2>
<ul>
  <li>Singleton</li>
  <li>Observer</li>
  <li>Builder</li>
  <li>Repository(Generic)</li>
  <li>Composite</li>
  <li>Command</li>
</ul>

<h2>Technologies Used</h2>
<ul>
  <li>Java</li>
  <li>Swing (with MigLayout for layout management)</li>
  <li>FlatLaf (for enhanced look and feel)</li>
  <li>MongoDB</li>
  <li>Argon2 (for secure password hashing)</li>
</ul>

<h2>Dependencies</h2>
<ul>
  <li><strong>MongoDB Driver Sync:</strong> org.mongodb:mongodb-driver-sync:5.0.1</li>
  <li><strong>MigLayout:</strong> com.miglayout:miglayout:3.7.4</li>
  <li><strong>FlatLaf:</strong> com.formdev:flatlaf:3.4</li>
  <li><strong>Argon2 JVM:</strong> de.mkammerer:argon2-jvm:2.11</li>
</ul>

<h2>Screenshots</h2>
<img width="1312" alt="Screenshot 2024-05-24 at 15 21 00" src="https://github.com/mehmetyurekli/Social/assets/113615465/905de0d8-e99e-460b-bf84-8937a28afe02">

<img width="1312" alt="Screenshot 2024-05-24 at 15 30 21" src="https://github.com/mehmetyurekli/Social/assets/113615465/f5708f21-6ac0-48f7-85db-742f4aa91cab">

<img width="1312" alt="Screenshot 2024-05-24 at 15 21 27" src="https://github.com/mehmetyurekli/Social/assets/113615465/cc77c0ea-1b41-48b4-b0a7-8f239f99611a">

<img width="1312" alt="Screenshot 2024-05-24 at 15 21 39" src="https://github.com/mehmetyurekli/Social/assets/113615465/49188666-5ddd-40b1-b928-8957f2a08b71">

<img width="1312" alt="Screenshot 2024-05-24 at 15 26 24" src="https://github.com/mehmetyurekli/Social/assets/113615465/2e92acb6-e010-4db4-ab3e-4ee770ddfa25">

<img width="1312" alt="Screenshot 2024-05-24 at 15 26 13" src="https://github.com/mehmetyurekli/Social/assets/113615465/a95a001b-f8d5-4189-af81-e413f7f221f9">

<img width="1312" alt="Screenshot 2024-05-24 at 15 21 52" src="https://github.com/mehmetyurekli/Social/assets/113615465/b9d1c9b3-ddec-443b-8a7c-d07fbe8b1444">

<img width="1312" alt="Screenshot 2024-05-24 at 15 22 36" src="https://github.com/mehmetyurekli/Social/assets/113615465/673b1aa6-46b2-4f60-834b-27aba55215ba">

<img width="1312" alt="Screenshot 2024-05-24 at 15 33 10" src="https://github.com/mehmetyurekli/Social/assets/113615465/512c1fc0-f86f-4a9d-be0e-5a7cf075a2ce">

This README file was created using ChatGPT.


