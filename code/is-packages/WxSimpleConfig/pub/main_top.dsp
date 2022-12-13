<html>
  <head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta http-equiv="Expires" content="-1">
    <link rel="stylesheet" type="text/css" href="top.css">
    <link rel="stylesheet" type="text/css" href="webMethods.css">
    <script src="/WmRoot/csrf-guard.js.txt"></script>
	<style>
</style>
  </head>

  <script>
    function launchHelp() {
        var url="";
        if (parent.menu != null) {
            var helpURLFromPage = parent.menu.document.forms["urlsaver"].helpURL.value;
            var helptopic = helpURLFromPage.lastIndexOf("=");
            var topic = helpURLFromPage.substring(helptopic+1);
                
            %invoke wm.server.admin:getHelpTopicUrlMappings%
                %ifvar result%
                    %loop result%
                        var id = "%value id%";
                            if(id==topic)
                            {
                                url="%value url%";
                            }
                    %endloop%
                %endif%
            %endinvoke%
            window.open(url, 'help', "directories=no,location=yes,menubar=yes,scrollbars=yes,status=yes,toolbar=yes,resizable=yes", true);
            }
        }
    
    function logIEout() {
      if (confirm("OK to log off?")) {
        return true;
      }
      else {
        return false;
      }
    } 
       
    function loadPage(url) {
      if (is_csrf_guard_enabled && needToInsertToken) {
        if (url.indexOf("?") != -1){
          url = url+"&"+ _csrfTokenNm_ + "=" + _csrfTokenVal_;
        }
        else {
          url = url+"?"+ _csrfTokenNm_ + "=" + _csrfTokenVal_;
        }
      } 
      window.location.replace(url);
    }

    function switchToQuiesceMode(mode) {
      var link = document.getElementById("Qlink");
      var delayTime = -1;
      if (mode == "false" || mode == false) {
        delayTime = prompt("OK to enter quiesce mode?\nSpecify the maximum number of minutes to wait before disabling packages:",0);
        if (delayTime == null) { 
          return false;
        }
        else {
          if (((parseFloat(delayTime) == parseInt(delayTime)) && !isNaN(delayTime)) && parseInt(delayTime) >= 0) {
            link.href = "quiesce-report.dsp?isQuiesceMode=true&timeout=" + delayTime;
            return true;
          }
          else {
            alert("Enter positive integer value.");
            return false;
          }
        }
      }
      if (mode == "true" || mode == true) {
        if (confirm("OK to exit quiesce mode?")) {
          link.href = "quiesce-report.dsp?isQuiesceMode=false";
          return true;
        }
        else {
          return false;
        }
      }
      return false;
    }

    function displayMode(mode) {
      var temp = document.getElementById("quiesceModeMessage");
      if (temp == null || temp == undefined) 
        return;

      if (mode == "true" || mode == true) {
        if (temp.innerHTML == '' || temp.innerHTML == '&nbsp;'){
          temp.style.display = "block";
          temp.innerHTML = "<center>Integration Server is running in quiesce mode.</center>";
        }    
      }
    }

    function displayMessage(mode, message) {
      var temp = document.getElementById("quiesceModeMessage");
      if (temp == null || temp == undefined) 
        return;
      if (mode == "true" || mode == true) {
        temp.style.display = "block";
        temp.innerHTML = "<center>"+message+"</center>";
      }
      else {
        temp.innerHTML = "";
        temp.style.display = "none";
      }
    }    
    

	
	
	 window.updateConfigLink = function (a){
       a.setAttribute('href', 'configframe.dsp');
    }
	
    %ifvar message%
      %ifvar norefresh%
      %else%
        setTimeout("loadPage('main_top.dsp')", 30000);
      %endif%
    %endif%
	
  </script>

  <body class="topbar" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">
  <form name="topform">
    <input type="hidden" name="realmURL" id="realmURL" value="">

    <table border=0 cellspacing=0 cellpadding=0 width="100%">
      <tr>
        <td>
          <table width="100%" cellspacing=0 cellpadding=0 border=0 align="center">
            <tr>
              %invoke wm.server.query:getServerInstanceName%

              <td class="saglogo">
                %ifvar productname equals('Integration Agent')%
                  <img src="images/sag_logo.png" height="100%" width="100%" /><br/>
                %else%
                  <img src="images/sag_logo.png" /><br/>
                %endif%
              </td>

				<td class="saglogo" align="center"> <br>
                  <img src="images/WxSimpleConfig.png" height="60px" width="400px"/>
				</td>
				<td class="keymessage" 
					id="quiesceModeMessage" 
					name="quiesceModeMessage" width="20%"
					style="display: none">
				</td>

              %ifvar adapter%
              %else%
                %invoke wm.server.query:isSafeMode%
                  %ifvar isSafeMode equals('true')%
                    %ifvar isSane equals('true')%
                      <td width="20%" class="keymessage">
                        <center>
                          SERVER IS RUNNING IN SAFE MODE.<br/>Master password sanity check failed - invalid master password provided. 
                        </center>
                      </td>
                    %else%
                      <td width="20%" class="keymessage" >
                        <center>
                          SERVER IS RUNNING IN SAFE MODE
                        </center>
                      </td>
                    %endif%
                  %endif%
                %endinvoke%
               %endif%
            </tr>
          </table>
        </td>

        <td nowrap class="topmenu" width="25%">
          %invoke wm.server:getSessionID% 
            %ifvar sessionid not equals('null')%
            %endif%    
                
            %ifvar adapter%
              <a href='javascript:window.parent.close();'>Close Window</a>
              %ifvar adapter equals('SAP')%
                 <!--a target="body" href="DocWeb/index.html">About</a!-->
              %endif%
			   <a target="body" href="DocWeb/index.html">About</a>
              %ifvar help%
                | <a target='adapter-body' onclick="launchHelp();return false;" href='#'>Help</A>
              %endif%
            %else%
              %invoke wm.server.quiesce:getCurrentMode%
                %ifvar isIntegrationAgent equals('false')%
                  %ifvar isQuiesceMode equals('true')%
                    <a target="body" href="quiesce-report.dsp" onclick="return switchToQuiesceMode('%value isQuiesceMode encode(javascript)%');" id="Qlink">Exit Quiesce Mode</a>
                  %else%
                    <!--a target="body" href="quiesce-report.dsp" onclick="return switchToQuiesceMode('%value isQuiesceMode encode(javascript)%');" id="Qlink">Enter Quiesce Mode</a> <!-->
                  %endif%
									<script>displayMode("%value isQuiesceMode encode(javascript)%");</script>
                 
                %endif%
              %endinvoke%
                            <a target='if3' href='/WmRoot/top-logoff.dsp?sessionid=%value sessionid%' onclick="return logIEout();" >Log Off</a>
                            |
                            <a target='if3' href="DocWeb/index.html">About</a>

            %endif%
          %endinvoke%
        </td>
      </tr>
    </table>
    </td>
    </tr>
    </table>
	</form>
	
  </body>
</html>
