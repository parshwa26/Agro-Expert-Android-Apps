<!doctype html>
<html lang="en">


<!-- Mirrored from demos.creative-tim.com/material-dashboard/examples/dashboard.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 20 Mar 2018 03:27:17 GMT -->
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
        $cid=$_GET['cid'];
        $_SESSION['cid']=$cid;


        $sql=mysqli_query($con,"select * from category where cid='$cid'");
        if($row=mysqli_fetch_array($sql)){
            // $sql1=mysqli_query($con,"select * from category where cid='".$row['pid']."'");
            // if($row1=mysqli_fetch_array($sql1)){

                $subcategory=$row['ecategory_flag'];
                $subcategory1=$row['gcategory_flag'];
                $subcategory2=$row['hcategory_flag'];
                //$category=$row1['ecategory_flag'];


                        
        }
    ?>


    <div class="wrapper">
        <div class="sidebar" data-color="purple" data-image="../assets/img/sidebar-1.jpg">
            <!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
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
                        <a href="addexpert.php">
                            <i class="fa fa-plus-square"></i>
                            <p>Add Expert</p>
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
                    <li class="active">
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
                    <li>
                        <a href="faqs.php">
                            <i class="material-icons">person</i>
                            <p>FAQs</p>
                        </a>
                    </li>
                    <li>
                        <a href="addfaqs.php">
                            <i class="fa fa-plus-square"></i>
                            <p>Add FAQs</p>
                        </a>
                    </li>
                   <!--  <li>
                        <a href="table.html">
                            <i class="material-icons">content_paste</i>
                            <p>Table List</p>
                        </a>
                    </li> 
                    <li>
                        <a href="typography.html">
                            <i class="material-icons">library_books</i>
                            <p>Typography</p>
                        </a>
                    </li>
                    <li>
                        <a href="icons.html">
                            <i class="material-icons">bubble_chart</i>
                            <p>Icons</p>
                        </a>
                    </li>
                    <li>
                        <a href="maps.html">
                            <i class="material-icons">location_on</i>
                            <p>Maps</p>
                        </a>
                    </li>
                    <li>
                        <a href="notifications.html">
                            <i class="material-icons text-gray">notifications</i>
                            <p>Notifications</p>
                        </a>
                    </li>
                    <li class="active-pro">
                        <a href="upgrade.html">
                            <i class="material-icons">unarchive</i>
                            <p>Upgrade to PRO</p>
                        </a>
                    </li>-->
                </ul>
            </div>
        </div>
        <div class="main-panel">
            <h3 style="padding: 10px 0px 0px 20px;">Admin Dashboard</h3>
            <a href="index.php"><button class="btn btn-primary" style="float: right; margin-top: -50px;">Log Out</button></a>
            <div class="content">
                <div class="container-fluid">
                    <div class="card">
                        <div class="card-header" data-background-color="purple" >
                            <h4 class="title">Edit Sub Category</h4>
                        </div>
                        <form action="editsubcategory_back.php" method="post">
                        <div class="form-group label-floating" style="padding: 10px;">
                            
                            <label class="control-label">Edit Sub Category</label>
                            <input type="text" class="form-control" value="<?php echo $subcategory?>" name="newcat1">
                            
                        </div>
                        <div class="form-group label-floating" style="padding: 10px;">
                            
                            <label class="control-label">Edit Sub Category</label>
                            <input type="text" class="form-control" value="<?php echo $subcategory1?>" name="newcat2">
                            
                        </div>
                        <div class="form-group label-floating" style="padding: 10px;">
                            
                            <label class="control-label">Edit Sub Category</label>
                            <input type="text" class="form-control" value="<?php echo $subcategory2?>" name="newcat3">
                            
                        </div>
                        <button type="submit" class="btn btn-primary pull-right" style="margin-right: 10px;">Update Subs Category</button>
                        </form>
                    </div>
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
<script type="text/javascript">
    $(document).ready(function() {

        // Javascript method's body can be found in assets/js/demos.js
        demo.initDashboardPageCharts();

    });
</script>


<!-- Mirrored from demos.creative-tim.com/material-dashboard/examples/dashboard.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 20 Mar 2018 03:27:17 GMT -->
</html>