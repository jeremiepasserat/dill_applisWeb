<?php    
    /* code pour la connexion au WebService */
    session_start();
    require "./files_json/Cservice.php";
    $Service = new CService;
    
    //on verifie que le formulaire est remplie
    if( isset($_POST['id']) && isset($_POST['password'])){
        $_SESSION['id'] = htmlspecialchars($_POST['id']);
        $_SESSION['password'] = htmlspecialchars($_POST['password']);
        $token = $Service::connectUserToWS($_SESSION['id'], $_SESSION['password']);
        $_SESSION['token'] = $token;
    }
    //on verifie si l'utilisateur peut se connecter (avec notament token)
    if(!isset($_SESSION['id']) || !isset($_SESSION['id']) || !isset($token)){
        //retourne sur la page de connexion
        $_SESSION["fail_connexion"]=1;
        header("Location:index.php");
        
    }
    
    /*Code basee sur les fichiers json (temporaire)*/
    /*
    //les donnees recuperees depuis le fichier json
    $file = './files_json/allScores.json';
    //on mets le contenu dans une variable
    $data = file_get_contents($file);
    //on décode le contenu du flux sous forme de tableau
    $array_data = json_decode($data, true);
    */
    
    
    //tableau contenant tous les utilisateurs avec leurs scores
    //$array_score_utilisateur = $array_data[0]['allScores'];
    $array_score_utilisateur = CService::postPatch($token[1], null, "GET", "allScores");;
    $array_score_total = array();

    //on recuperer les scores totaux
    foreach ( $array_score_utilisateur[1] as $utilisateur){
        $score_total_util = 0;
        foreach($utilisateur['scores'] as $jeu){
            $score_total_util += $jeu['scoreJeu'];
        }
        $array_score_total[$utilisateur['pseudo']] = $score_total_util;
    }
    arsort($array_score_total, SORT_NUMERIC);
    
?>

<!DOCTYPE html>
<html>
<title>Accueil</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="./CSS/style_consultation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right"><img src="./Images/logo.PNG" alt="Logo de l'appli" style="width:30px" /></span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s4">
      <img src="/w3images/avatar2.png" class="w3-circle w3-margin-right" style="width:46px">
    </div>
    <div class="w3-col s8 w3-bar">
      <span>Welcome, <strong>Mike</strong></span><br>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
    </div>
  </div>
  <hr>
  <div class="w3-container">
    <h5>Menu</h5>
  </div>
  <div class="w3-bar-block">
      <a href="./Page_Principale.php" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-home fa-fw"></i>  Accueil</a>
    <a href="./Classement_general.php" class="w3-bar-item w3-button w3-padding "><i class="fa fa-trophy fa-fw"></i>  Classement</a>
    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-newspaper-o fa-fw"></i>  Magazine CMJ</a>
    <a href="./Gestion_defis.php" class="w3-bar-item w3-button w3-padding"><i class="fa fa-pencil-square-o fa-fw"></i>  Défis</a>
    <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-gamepad fa-fw"></i>  Jeux</a>
    <br><br>
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i> Nombre de :</b></h5>
  </header>

  <div class="w3-row-padding w3-margin-bottom">
    <div class="w3-quarter">
      <div class="w3-container w3-red w3-padding-16">
        <div class="w3-left"><i class="fa fa-envelope-o w3-xxxlarge"></i></div>
        <div class="w3-right">
          <h3>52</h3>
        </div>
        <div class="w3-clear"></div>
        <h4>Article</h4>
      </div>
    </div>
    <div class="w3-quarter">
      <div class="w3-container w3-orange w3-text-white w3-padding-16">
        <div class="w3-left"><i class="fa fa-users w3-xxxlarge"></i></div>
        <div class="w3-right">
          <h3><?php echo count($array_score_utilisateur); ?></h3>
        </div>
        <div class="w3-clear"></div>
        <h4>Inscrit</h4>
      </div>
    </div>
  </div>

  <div class="w3-panel">
    <div class="w3-row-padding" style="margin:0 -16px">
        <h5>Les 10 premiers joueurs</h5>
        <table class="w3-table w3-striped w3-white">
            <tr>
                <th></th>
                <th>Nom utilisateur</th>
                <th>Score</th>
                <th>Classement</th>
            </tr>
            <?php
                    
                //On affiche les 10 premiers utilisateurs avec leur classement.
                $classement = 1;
                foreach ($array_score_total as $row){
                    echo "<tr>".
                        "<td><i class=\"fa fa-user w3-text-blue w3-large\"></i>";
                        switch ($classement){
                            case 1:
                                echo "<i class=\"fa fa-trophy w3-text-yellow w3-large \">";
                                break;
                            case 2:
                                echo "<i class=\"fa fa-trophy w3-text-brown w3-large \">";
                                break;
                            case 3:
                                echo "<i class=\"fa fa-trophy w3-text-gray w3-large \">";
                                break;
                        }
                        echo "</td><td>".key($array_score_total)."</td>".
                        "<td>".current($array_score_total)."</td>".
                        "<td>".$classement."</td>";
                    next($array_score_total);
                    $classement++;
                    echo "</tr>";
                    if ($classement > 10){
                        break;
                    }
                }
            ?>
        </table>
      </div>
  </div>
  <hr>
  

  <!-- Footer -->
  <footer class="w3-container w3-center w3-padding-16 w3-light-grey">
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
  </footer>

  <!-- End page content -->
</div>


</body>
</html>
