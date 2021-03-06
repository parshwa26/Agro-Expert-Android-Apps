



<!doctype html>
<html lang="en">


<!-- Mirrored from demos.creative-tim.com/material-dashboard/examples/user.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 20 Mar 2018 03:27:17 GMT -->
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Agro</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />
    <!-- Canonical SEO -->
    
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
    <!--  Material Dashboard CSS    -->
    <link href="../assets/css/material-dashboard5438.css?v=1.2.0" rel="stylesheet" />
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="../assets/css/demo.css" rel="stylesheet" />
    <!--     Fonts and icons     -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet' type='text/css'>

</head>

<body>
    <?php

    include('connection.php');
    session_start();
    $fid=$_POST['fid'];
    $_SESSION['fid']=$fid;


    $sql=mysqli_query($con,"select * from faq where fid='$fid'");
    if($row=mysqli_fetch_array($sql)){
        $hquestion=$row['hquestion'];
        $gquestion=$row['gquestion'];
        $equestion=$row['equestion'];
        $hanswer=$row['hanswer'];
        $ganswer=$row['ganswer'];
        $eanswer=$row['eanswer'];

    }


    ?>



    <div class="wrapper">
        <div class="sidebar" data-color="purple" data-image="../assets/img/sidebar-1.jpg">
            
    <div class="logo">
        <center>
            <img src="../assets/img/logo.png" style="height: 50px;">
        </center>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li>
                <a href="dashboard.php">
                    <i class="material-icons">dashboard</i>
                    <p>Dashboard</p>
                </a>
            </li>
            <li>
                <a href="expert.php">
                    <i class="material-icons">person</i>
                    <p>Expert</p>
                </a>
            </li>


            <li>
                <a href="category.php">
                    <i class="material-icons">person</i>
                    <p>Category</p>
                </a>
            </li>
            <li>
                <a href="addcategory.php">
                    <i class="fa fa-plus-square"></i>
                    <p>Add category</p>
                </a>
            </li>
            <li>
                <a href="subcategory.php">
                    <i class="material-icons">person</i>
                    <p>Sub Category</p>
                </a>
            </li>
            <li>
                <a href="addsubcategory.php">
                    <i class="fa fa-plus-square"></i>
                    <p>Add Sub category</p>
                </a>
            </li>
            <li class="active">
                <a href="faqs.php">
                    <i class="material-icons">person</i>
                    <p>FAQs</p>
                </a>
            </li>
            <li >
                <a href="addfaqs.php">
                    <i class="fa fa-plus-square"></i>
                    <p>Add FAQs</p>
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="main-panel">
    <h3 style="padding: 10px 0px 0px 20px;">Admin Dashboard</h3>
    <a href="index.php"><button class="btn btn-primary" style="float: right; margin-top: -50px;">Log Out</button></a>
    <div class="content" style="margin-top: 0px;">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header" data-background-color="purple">
                            <h4 class="title">Edit FAQs</h4>
                        </div>
                        <div class="card-content">
                            <form action="editfaqs_back.php" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group label-floating">
                                            <label class="control-label">English Question</label>
                                            <input type="text" value="<?php echo $equestion?>" class="form-control" name="equestion">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group label-floating">
                                            <label class="control-label">English Answer</label>
                                            <input type="text" value="<?php echo $eanswer?>" class="form-control" name="eanswer">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group label-floating">
                                            <label class="control-label">Gujarati Question</label>
                                            <input type="text" value="<?php echo $gquestion?>" class="form-control" name="gquestion">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group label-floating">
                                            <label class="control-label">Guajrati Answer</label>
                                            <input type="text" value="<?php echo $ganswer?>" class="form-control" name="ganswer">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group label-floating">
                                            <label class="control-label">Hindi Question</label>
                                            <input type="text" value="<?php echo $hquestion?>" class="form-control" name="hquestion">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group label-floating">
                                            <label class="control-label">Hindi Answer</label>
                                            <input type="text" value="<?php echo $hanswer?>" class="form-control" name="hanswer">
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <button type="submit" name="submit" class="btn btn-primary pull-right">Update FAQs</button>
                            <div class="clearfix"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="footer">

</footer>
<div class="fixed-plugin">

</div>
</div>
</div>
</body>
<!--   Core JS Files   -->
<script src="../assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../assets/js/material.min.js" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="../assets/js/chartist.min.js"></script>
<!--  Dynamic Elements plugin -->
<script src="../assets/js/arrive.min.js"></script>
<!--  Sharrre Plugin -->
<script src="../assets/js/jquery.sharrre.js"></script>
<!--  PerfectScrollbar Library -->
<script src="../assets/js/perfect-scrollbar.jquery.min.js"></script>
<!--  Notifications Plugin    -->
<script src="../assets/js/bootstrap-notify.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBSaSxv01RBLnlu5EyBHLs57s-IquPaows"></script>
<!-- Material Dashboard javascript methods -->
<script src="../assets/js/material-dashboard5438.js?v=1.2.0"></script>
<!-- Material Dashboard DEMO methods, don't include it in your project! -->
<script src="../assets/js/demo.js"></script>



<!-- Mirrored from demos.creative-tim.com/material-dashboard/examples/user.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 20 Mar 2018 03:27:17 GMT -->
</html>