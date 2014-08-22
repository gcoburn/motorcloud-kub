<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Motorcloud Bass Player Search</title>
<link href="styles/styles.css" rel="stylesheet" type="text/css" />


    <!-- Bootstrap core CSS -->
    <link href="./vscripts/bootstrap.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="./vscripts/docs.css" rel="stylesheet"/>

    <link href="./vscripts/github.min.css" rel="stylesheet"/>

    <link href="./vscripts/parsley.css" rel="stylesheet"/>
    <style>
      body {
        color: #111111;
      }
      .row {
        margin-top: 10px;
      }
      .code-block {
        margin-top: 20px;
      }
      .hidden {
        display: none;
      }
      .row h2 {
        color: #FF851B;
      }
      .row p {
        text-align: justify;
      }
      .first {
        padding-left: 15px;
        border-left: 2px solid #FF851B;
      }
      .second {
        padding-left: 15px;
        border-left: 2px solid #5bc0de;
      }
      .validate {
        margin-top: 10px;
      }
      h4 {
        margin-bottom: 10px;
      }
      .invalid-form-error-message {
        margin-top: 10px;
        padding: 5px;
      }
      .invalid-form-error-message.filled {
        border-left: 2px solid red;
      }
    </style>
    
    
    
</head>

<body background="images/form_BG.jpg" style="background-repeat:no-repeat; background-color:#000000; background-position:center top;">

<div id="container"><!--FORMS CONTAINER-->
   
<form id="myform"  action="enter" class="cssform" method="post">
      
	 	<!--FORM LEFT-->
		<div class="forms" id="left">
				<p>
				<label for="firstname">FIRST NAME: </label> 
				<input type="text" id="user" value="" name="firstname" maxlength="100" data-parsley-group="block1" data-parsley-trigger="change" required="" data-parsley-id="6293"/>
				</p>
				<p>
				<label for="lastname">LAST NAME: </label> 
				<input type="text" id="emailaddress" value="" name="lastname" maxlength="100" data-parsley-group="block1" data-parsley-trigger="change" required="" data-parsley-id="6293"/>
				</p>
        		<p>
				<label for="email">EMAIL: </label>
				<input type="email" id="emailaddress" value="" name="emailaddress" maxlength="100" data-parsley-group="block1" data-parsley-trigger="change" required="" data-parsley-id="8265"/>
				</p>
        		<p>
				<label for="twitterid">TWITTER ID:</label>
				<input type="text" id="emailaddress" value="" name="twitterid"  maxlength="100" data-parsley-group="block1" data-parsley-trigger="change" required="" data-parsley-id="6293"/>
				</p>
		  <div style="color:#ffffff; width:400px; margin-top:20px; text-align:center; font-family:Arial, Helvetica, sans-serif; margin: 33px 50px;"><strong>This is how we get a hold of you.</strong><br />
          		<br />We <strong>guarantee</strong> that your information <strong>will not</strong> be shared with any third party for any reason. Your information will <strong>only</strong> be used in relation to this demonstration.</div>
		</div><!--FORM LEFT END-->
		
    	
		
      	<div class="forms" id="right"><!--FORM RIGHT-->
		
        	<div id="myform" class="cssform">
          			<p>
					<label for="stagename" style="color:#FFFFFF; text-shadow: -1px -1px 1px #000000;">STAGE NAME:</label>
					<input type="text" id="user" value="" name="sname" maxlength="100" data-parsley-group="block1" data-parsley-trigger="change" required="" data-parsley-id="6293"/>
					</p>
          			<p>
					<label for="catchphrase" style="color:#FFFFFF; text-shadow: -1px -1px 1px #000000;">CATCH PHRASE:</label>
          			<input type="text" id="emailaddress" value="" name="cphrase" maxlength="100" data-parsley-group="block1" data-parsley-trigger="change" required="" data-parsley-id="6293"/>
					</p>
					<p>
					<label for="minibio" style="color:#FFFFFF; text-shadow: -1px -1px 1px #000000;">MINI BIO:</label> 
        	  		<textarea id="comments" rows="5" cols="25" name="minibio" maxlength="500" data-parsley-group="block1" data-parsley-trigger="keyup" required=""  data-parsley-minlength="20" data-parsley-maxlength="500" data-parsley-minlength-message="Come on! If you wanna rock the cloud with us you gotta give us more than 20 characters!!!" data-parsley-validation-threshold="10" data-parsley-id="2026"></textarea></p>

				 <div style="color:#ffffff; width:400px; margin-top:20px; text-align:left; font-family:Arial, Helvetica, sans-serif; margin: 35px 0 33px;">
					<strong>This is how you could win. :)</strong></div>
			
				
				
				<div style="color:#ffffff; width:450px; margin-top:20px; text-align:left; font-family:Arial, Helvetica, sans-serif; margin: 50px 0 33px;"><input type="submit" class="btn btn-default validate" id="submitbutton" value="Submit" /></div>
					

      	</div>
			
		</div><!--FORM RIGHT-->
		
		</form>

</div><!--FORMS CONTAINER END-->

<p style="margin-bottom:200px;">&nbsp;</p>



    <script type="text/javascript" src="./vscripts/jquery.min.js"></script>
    <script type="text/javascript" src="./vscripts/affix.js"></script>
    <script type="text/javascript" src="./vscripts/highlight.min.js"></script>

    <script type="text/javascript" src="./vscripts/parsley.js"></script>
    <script type="text/javascript">

      $(document).ready(function () {
        $('#myform').parsley().subscribe('parsley:form:validate', function (formInstance) {
          if (formInstance.isValid('block1', true) || formInstance.isValid('block2', true)) {
            $('.invalid-form-error-message').html('');
            return;
          }

          formInstance.submitEvent.preventDefault();
          $('.invalid-form-error-message')
            .html("All fields required.")
            .addClass("filled");
          return;
        });
      });


    </script>
  
  
  
  
  
</body>
</html>
