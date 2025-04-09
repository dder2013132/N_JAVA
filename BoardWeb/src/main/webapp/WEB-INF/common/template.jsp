    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>Simple Sidebar - Start Bootstrap Template</title>
  <!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="css/styles.css" rel="stylesheet" />
  <style>
    .iconimage {
      border-radius: 50%;
      width: 30px;
      height: 30px;
    }

    .noimage {
      height: 0;
      width: 0;
    }
    
	#loader {
	  border: 6px solid #f3f3f3;
	  border-top: 6px solid #3498db;
	  border-radius: 50%;
	  width: 40px;
	  height: 40px;
	  animation: spin 1s linear infinite;
	  margin: 20px auto;
	}
	
	@keyframes spin {
	  0% { transform: rotate(0deg); }
	  100% { transform: rotate(360deg); }
	}
    
    .loading {
      margin: 0 auto;
      text-align: center;
    }
  </style>
</head>

<body>
  <div class="d-flex" id="wrapper">
    <!-- Sidebar-->
    <tiles:insertAttribute name="menu" />
    <!-- Page content wrapper-->
    <div id="page-content-wrapper">
      <!-- Top navigation-->
      <tiles:insertAttribute name="header" />
      <!-- Page content-->
      <div class="container-fluid">
	    <div id="loader" style="display: none;">🔄 불러오는 중...</div>
        <tiles:insertAttribute name="body" />
      </div>
    </div>
  </div>
  <tiles:insertAttribute name="footer" />
</body>

</html>