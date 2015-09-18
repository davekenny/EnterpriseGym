<%-- 
    Document   : header
    Created on : 15-Sep-2015, 12:44:38
    Author     : Dave
--%>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Enterprise Gym</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/half-slider.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <!-- Country Drop-down CSS -->
    <link href="${pageContext.request.contextPath}/css/flags.css" rel="stylesheet">
    
    <script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <div class="navigation-bar">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        
                    <li class="homeButton${pageContext.request.getServletPath() eq '/index.jsp' ? ' active' : ''}">
                        <a href="${pageContext.request.contextPath}/Home"><img src="${pageContext.request.contextPath}/Pictures/logo-eg.png"></a>
                    </li>
                    <li class="aboutButton${pageContext.request.getServletPath() eq '/about.jsp' ? ' active' : ''}">
                        <a href="${pageContext.request.contextPath}/About">About</a>
                    </li>
                    <li class="eventsButton${pageContext.request.getServletPath() eq '/events.jsp' ? ' active' : ''}">
                        <a href="${pageContext.request.contextPath}/Events">Events</a>
                    </li>
                    <li class="newsButton${pageContext.request.getServletPath() eq '/news.jsp' ? ' active' : ''}">
                        <a href="${pageContext.request.contextPath}/News">News</a>
                    </li>                 
                    <li class="activitesButton${pageContext.request.getServletPath()eq '/activities.jsp' ? ' active' : ''}">
                        <a href="${pageContext.request.contextPath}/Activities">Activities</a>
                    </li>
                    <li class="quizesButton${pageContext.request.getServletPath() eq '/quizes.jsp' ? ' active' : ''}">
                        <a href="${pageContext.request.contextPath}/Quizes">Online Tests</a>
                    </li>
                    <li class="profileButton${pageContext.request.getServletPath() eq '/profile.jsp' ? ' active' : ''}">
                        <a href="${pageContext.request.contextPath}/Profile">My eGym</a>
                    </li>
                </ul>

                
        <ul class="nav navbar-nav navbar-right">        
          <li class="logintext"> Logged in as: <%=session.getAttribute("username")%></li>
          <li>
              <a href="${pageContext.request.contextPath}/LogOut">Log Out</a></li>                  
            <li id="calendar-drop">
                <a data-toggle="collapse" data-target="#calendar" href="#calendar">
                    <div class="header-calendar">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </div>
                </a>
            </li>
        </ul>
         
               
            </div>
          
            <!-- /.navbar-collapse -->
        </div>
         
        <!-- /.container -->
    </nav>
</div>
                   
            <div class="calendar">
                <div id="calendar" class="collapse">
                    <div class="pull-right">
                        <iframe src="https://www.google.com/calendar/embed?showTabs=0&amp;showCalendars=0&amp;showTz=0&amp;mode=AGENDA&amp;height=500&amp;wkst=1&amp;bgcolor=%23FFFFFF&amp;src=m9941bpd6nhji35pjjp3blv8do%40group.calendar.google.com&amp;color=%23853104&amp;ctz=Europe%2FLondon" style=" border-width:0 " width="400" height="500" frameborder="0" scrolling="no"></iframe>
                    
                    </div>
                </div>
            </div>


    <!-- Half Page Image Background Carousel Header -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for Slides -->
        <div class="carousel-inner">
            <div class="item active">
                <!-- Set the first background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${pageContext.request.contextPath}/Pictures/banner.jpg');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the second background image using inline CSS below. -->
                <div class="fill" style="background-image:url('${pageContext.request.contextPath}/Pictures/fun-people-only.png');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            <div class="item">
                <!-- Set the third background image using inline CSS below. -->
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Three');"></div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
        
 

    </header>