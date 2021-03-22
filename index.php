<?php
    session_start();
?>

<!DOCTYPE html>
<html>
    <title>Connexion</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="./CSS/style_consultation.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
    body,h1,h2,h3,h4,h5 {font-family: "Poppins", sans-serif}
    body {font-size: 16px;}
    img {margin-bottom: -8px;}
    .mySlides {display: none;}
    </style>
    <body class="w3-content w3-black" style="max-width:1500px;">

    <!-- Header with Slideshow -->
    <header class="w3-display-container w3-center">
      <div class="w3-animate-opacity">
        <img class="w3-image" src="./Images/Image_mairie.jpg" alt="Image mairie" style="min-width:500px" width="1000" height="500">
        <div class="w3-display-left w3-padding w3-hide-small" style="width:35%">
          <div class="w3-black w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
            <h1 class="w3-xlarge">Amusez-vous sur l'application de la commune</h1>
            <hr class="w3-opacity">
            <p>Super simple, il suffit de cliquer sur le bouton ci-dessous!</p>
            <p><a href='#'><button class="w3-button w3-block w3-orange w3-round">JOUER </button></a></p>

          </div>
        </div>
        <div class="w3-display-right w3-padding w3-hide-small" style="width:35%">
          <div class="w3-white w3-opacity w3-hover-opacity-off w3-padding-large w3-round-large">
            <h1 class="w3-xlarge">Se connecter</h1>
            <?php
                if(isset($_SESSION["fail_connexion"]) && $_SESSION["fail_connexion"]==1){
                    echo "<p class= \"w3-text-red w3-large \"> Nom utilisateur ou mot de passe incorrect</p>";
                    $_SESSION["fail_connexion"]=0;
                }
            ?>
            <hr class="w3-opacity">
            <form action="Page_Principale.php" method="post">
                <p><input class="w3-input w3-border w3-light-grey" type="text" name="id" id="id" placeholder="Nom d'utilisateur ou email" required ></p>
                <p><input class="w3-input w3-border w3-light-grey" type="password" name="password" id="password" placeholder="Mot de passe" required ></p>
                <p><input type="submit" class="w3-button w3-blue w3-padding-large" value="Connexion"></p>
            </form>

          </div>
        </div>
      </div>


    </header>

    <!-- The App Section -->
    <div class="w3-padding-64 w3-white">
      <div class="w3-row-padding">
        <div class="w3-col l8 m6">
          <h1 class="w3-jumbo"><b>Application de la commune Lamotte-Beuvron</b></h1>
          <h1 class="w3-xxxlarge w3-text-light-blue"><b>Qu'est-ce que c'est ?</b></h1>
          <p><span class="w3-xlarge">Bienvenu,</span> tous d'abord ce site est réservé au personnel de la commune afin qu'ils puissent réaliser des tâches
              administratives et de gestions. Si vous n'êtes pas membre du personelle de la commune il faut cliquer sur le bouton jouer ci-dessus pour accèder
              directement à l'application.</p>
        </div>
      </div>
    </div>



    <!-- Contact Container -->
    <div class="w3-container w3-padding-64 w3-white w3-center" id="contact">
        <div class="w3-padding-16"><span class="w3-large w3-border-teal w3-bottombar">Contacts</span></div>
            <p><i class="fa fa-map-marker w3-text-teal w3-medium"></i>  CMairie de Lamotte-Beuvron 41, Avenue de l'Hôtel de Ville 
                41600 Lamotte-Beuvron</p>
            <p><i class="fa fa-phone w3-text-teal w3-medium"></i>  02 54 88 84 84</p> 
            <a href='https://www.facebook.com/villedelamottebeuvron/'><i class="fa fa-facebook-official w3-hover-opacity w3-xlarge"></i></a>
            <a href='https://www.lamotte-beuvron.fr/'><i class="fa fa-globe w3-hover-opacity w3-xlarge"></i></a>
    </div>




    <!-- Footer -->
    <footer class="w3-container w3-padding-32 w3-light-grey w3-center w3-xlarge">
      <p class="w3-medium">Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank" class="w3-hover-text-green">w3.css</a></p>
    </footer>



    </body>
</html>