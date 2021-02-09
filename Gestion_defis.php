<?php ?>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Classement général</title>
        <link rel="stylesheet" href="./CSS/style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="block_page">
            <header>
                <div id="titre_principale">
                    <div id="logo">
                        <img src="./Images/logo.PNG" alt="Logo de l'appli" />
                        <h1>Lamotte Beuvron</h1>
                    </div>
                </div>                   
            </header>
            <nav>
                <ul>
                    <li><a href="./connexion.php">Page principal</a></li>
                    <li><a href="./Classement_general.php">Classement général</a></li>
                    <li><a href="#">Magazine CMJ</a></li>
                    <li><a href="#">Gestion des jeux</a></li>
                </ul>
            </nav>
            <?php 
                echo "Welcome to WayToLearnX.com</br>"; 
                echo "La page sera actualisée toutes les 3 secondes.";

                header("refresh: 3"); 

                exit; 
              ?>
        </div>
    </body>
</html>
