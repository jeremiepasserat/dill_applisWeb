<?php
    session_start();
    
    if( isset($_POST['email']) && isset($_POST['password'])){
        $_SESSION['email'] = htmlspecialchars($_POST['email']);
        $_SESSION['password'] = htmlspecialchars($_POST['password']);
        Service::connectToWs("",$_SESSION['email']);

    }else if(!isset($_SESSION['email']) || !isset($_SESSION['password'])){
        header("Location:index.html");
        //include('./index_error_login.html');
    }
    
    //les donnees recuperees depuis le fichier json
    $file = './files_json/allScores.json';
    //on mets le contenu dans une variable
    $data = file_get_contents($file);
    //on décode le contenu du flux sous forme de tableau
    $array_data = json_decode($data, true);
    //tableau contenant tous les utilisateurs avec leurs scores
    $array_score_utilisateur = $array_data[0]['allScores'];
?>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Page Principal</title>
        <link rel="stylesheet" href="./CSS/style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="block_page">
            <header>
                <div id="titre_principale">
                    <div id="logo">
                        <a href="Page_Principale.php"><img src="./Images/logo.PNG" alt="Logo de l'appli" /><a/>
                        <h1>Lamotte Beuvron</h1>
                    </div>
                </div>                   
            </header>
            <nav>
                <ul>
                    <li><a href="./Classement_general.php">Classement général</a></li>
                    <li><a href="#">Magazine CMJ</a></li>
                    <li><a href="./Gestion_defis.php">Gestion des défis</a></li>
                    <li><a href="#">Gestion des jeux</a></li>
                </ul>
            </nav>
            <div id='banniere_image'>
                <div id='banniere_description'>
                    Commune Lamotte-Beuvron
                    <a href='https://www.lamotte-beuvron.fr/index.php' class='bouton_rouge'>Voir le site<img src='./Images/flecheblanche.png' alt='' /></a>
                </div>
            </div>
            
            <section>
                <article>
                    <h1>Application de la commune de Lamotte-Beuvron</h1>
                    <p id='page_principal'>
                        Bonjours <?php echo $_SESSION['email'];?>,<br/> vous trouverez ci-dessus un menu permettant d'accèder à différentes fonctionnalité.
                    </p>
                </article>
                <aside>
                    <h1>Quelques chiffres :</h1>
                    <img src="Images/logo_mairie.png" alt="logo de la mairie"/>
                    <p>Nombre d'inscrit : <?php echo count($array_score_utilisateur); ?></p>
                    <p>Nombre de défis : 0</p>
                    <p>Nombre de jeux : 0</p>
                    <p>Nombre d'article dans le CMJ : 0</p>
                </aside>
            </section>
            <div class='Recherche'>
                <p>Consulter le profil d'un utilisateur :</p>
                <form action="Consultation_user.php" method="post">
                    <label for="pseudo">Pseudo</label> :
                    <input type="text" name="pseudo" id="pseudo"  required />
                    <input type="submit" value="Rechercher" />
                </form>
            </div>
        </div>
    </body>
</html>
