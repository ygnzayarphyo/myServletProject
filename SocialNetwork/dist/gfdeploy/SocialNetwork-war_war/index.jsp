<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
        <script src="bootstrap-3.3.5-dist/js/jquery-2.1.4.min.js"></script>
        <script src="SpryAssets/GeneralForm.css"></script>
        <script>
            $(function(){
                $("#header").load("header.jsp");
                $("#footer").load("footer.html");
            });
        </script>
        <link rel="shortcut icon" type="image/x-icon" href="bootstrap-3.3.5-dist/MyLogo.png" />
        <link rel="bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
        <link rel="SpryAssets/GeneralForm.css">

        <!-- Start WOWSlider.com HEAD section --> <!-- add to the <head> of your page -->
        <link rel="stylesheet" type="text/css" href="engine0/style.css" />
        <script type="text/javascript" src="engine0/jquery.js"></script>
        <!-- End WOWSlider.com HEAD section -->
    </head>

    <body>        
        <div id="header"></div>

        <div id="main">
            <!-- Start WOWSlider.com BODY section --> <!-- add to the <body> of your page -->
            <div id="wowslider-container0">
                <div class="ws_images"><ul>
                        <li><img src="data0/images/img1.jpg" alt="" title="" id="wows0_0"/></li>
                        <li><img src="data0/images/img3.jpg" alt="" title="" id="wows0_2"/></li>
                </ul></div>
                <div class="ws_bullets"><div>
                        <a href="#" title=""><span><img src="data0/tooltips/img1.jpg" alt=""/>1</span></a>
                        <a href="#" title=""><span><img src="data0/tooltips/img2.jpg" alt=""/>2</span></a>
                        <a href="#" title=""><span><img src="data0/tooltips/img3.jpg" alt=""/>3</span></a>
                </div></div><div class="ws_script" style="position:relative;left:-99%"><a href="http://wowslider.com">css image gallery</a> by WOWSlider.com v8.2</div>
                <div class="ws_shadow"></div>
            </div>
            <script type="text/javascript" src="engine0/wowslider.js"></script>
            <script type="text/javascript" src="engine0/script.js"></script>
            <!-- End WOWSlider.com BODY section -->			<span class="icon-bar"></span>

        </div>

        <div id="footer">

        </div>

    </body>
</html>