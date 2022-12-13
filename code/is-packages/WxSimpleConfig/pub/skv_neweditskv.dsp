<html lang="en">
<head>
    <meta charset="UTF-8">
	<title>WxSimpleConfig</title>
    <style> 
.noshow { display: none; }
		thead {color:green;}
		tbody {color:#0000FF;}
		tfoot {color:red;}
		table,th,td
		{border:1px solid black;}
#pagination a {
display:inline-block;
margin-right:5px;

}

.pg-normal {
	color: black;
	font-weight: normal;
	text-decoration: none;    
	cursor: pointer;    
}
.pg-selected {
	color: black;
	font-weight: bold;        
	text-decoration: underline;
	cursor: pointer;
}
			
.loader{
  width: 50px;
  height: 50px;
  border-radius: 100%;
  position: relative;
  margin: 0 auto;
} 
#loader-1:before, #loader-1:after{
  content: "";
  position: absolute;
  top: -10px;
  left: -10px;
  width: 100%;
  height: 100%;
  border-radius: 100%;
  border: 10px solid transparent;
  border-top-color: #3498db;
}

#loader-1:before{
  z-index: 100;
  animation: spin 1s infinite;
}

#loader-1:after{
  border: 10px solid #ccc;
} 
@keyframes spin{
  0%{
    -webkit-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -o-transform: rotate(0deg);
    transform: rotate(0deg);
  }

  100%{
    -webkit-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    transform: rotate(360deg);
  }
} 

/* Added to manage errors tooltip */

/* Tooltip container */
.tooltip {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
}

/* Tooltip text */
.tooltip .tooltiptext {
    visibility: hidden;
    width: 300px;
    background-color: #555;
    color: #fff;
    text-align: center;
    padding: 5px 0;
    border-radius: 6px;

    /* Position the tooltip text */
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left: 50%;
    margin-left: -150px;

    /* Fade in tooltip */
    opacity: 0;
    transition: opacity 0.3s;
}

/* Tooltip arrow */
.tooltip .tooltiptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 50%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #555 transparent transparent transparent;
}

/* Show the tooltip text when you mouse over the tooltip container */
.tooltip:hover .tooltiptext {
    visibility: visible;
    opacity: 1;
}
    </style>
    <link rel="stylesheet" type="text/css" href="webMethods.css">
        <script type="text/javascript" src="paging.js"></script>
</head>
<script language="JavaScript">
	function forceObjectsStatusRefresh()
	{
		setValueForCheckBoxes();
		return true;
	}
		
    function setValueForCheckBoxes()
    {
        var node_list = document.getElementsByTagName('input');
 
        for (var i = 0; i < node_list.length; i++) {
            var node = node_list[i];
         
            if (node.getAttribute('type') == 'checkbox') {
                node.disabled = false;
                node.value = node.checked;
                
            }
        } 
    }
		
    function changeInputType(chkBox) {
        var oType = "text";
        if(chkBox.checked) {
            oType = "password";
        }
        var oldObject = document.getElementById("value");
        var newObject = document.createElement('input');
        newObject.type = oType;
        //if(oldObject.size) newObject.size = oldObject.size;
        if(oldObject.style.width)
			newObject.style.width = oldObject.style.width;
        if(oldObject.value) 
			newObject.value = oldObject.value;
        if(oldObject.name)
			newObject.name = oldObject.name;
        if(oldObject.id) 
			newObject.id = oldObject.id;
        if(oldObject.className) 
			newObject.className = oldObject.className;
        oldObject.parentNode.replaceChild(newObject,oldObject);
      return newObject;
    }
		
</script>
<body>
	<table style="width:100%" style="height:100%">
			<tbody><tr>
			<td class="breadcrumb" colspan="2">WxSimpleConfig &gt; Sections Management &gt; %value section% &gt; New Variable </td>
			</tr>
			</tbody>
	</table>

<hr>

%ifvar operation equals('edit')%
%invoke wx.simpleConfig.globalVariables.genericContent.skv.web:getValueForSkv%
%endinvoke%
%endif%

<form name = "SectionCreation" action = "/invoke/wx.simpleConfig.globalVariables.genericContent.skv.web/saveSkv">

<table id="OuterRealmStatus" border="2" style="width:100%" style="height:100%" class="tableView">
<tr>
	<td class="heading" colspan=13>Provide the information for the Variable</td>
	
</tr>		  

<tr>
<td>
    <input hidden="true" style="width: 500px" type="text" name="landingPage" id="landingPage" value="/WxSimpleConfig/skv_listsectioncontent.dsp?section=%value section%"/>
	<table style="width:100%" style="height:100%" class="tableView">
		<tbody>
			<tr >
			
				<td style="width:10%" border="0">Section Name: <br>
				</td>
				<td>
					<input style="width: 500px" type="text" name="section" value="%value section%" readonly autofocus/><br>
				</td>
			</tr>
			<tr >
			
				<td style="width:10%" border="0">Is secure: <br>
				</td>
				<td>
					%ifvar operation equals('add')%
						<input type="checkbox" name="isSecure" id="isSecure" onclick="changeInputType(this)" %ifvar isSecure equals('true')%checked%endif%>
					%endif% 
					%ifvar operation equals('edit')%
						<input type="checkbox" name="isSecure" id="isSecure" %ifvar isSecure equals('true')%checked value="true" %endif% disabled>
					%endif%
				</td>
			</tr>
			<tr >
			
				<td style="width:10%" border="0">Variable Name: <br>
				</td>
				<td>
					<input style="width: 500px" type="text" name="key" id="key" value="%value key%" autofocus/><br>
				</td>
			</tr>
			<tr >
			
				<td style="width:10%" border="0">Value: <br>
				</td>
				<td>
%ifvar operation equals('add')%
					<input style="width: 500px" type="text" name="value" id="value"  autofocus/><br>
%endif% 
%ifvar operation equals('edit')%
	%ifvar isSecure equals('true')%
					<input style="width: 500px" type="password" autocomplete="off" size="40" name="value" id="value" />
	%else%
					<input style="width: 500px" type="text" name="value" id="value" value="%value value encode(htmlattr)%" autofocus/><br>
	%endif% 
%endif%

				</td>
			</tr>
		</tbody>
	</table>
	
</td>
</tr>
<tr>
<td>
<fieldset id="Actions">
	<legend >Actions</legend>

	<table style="border:0">
	<tr>
	<td style="width:10%; border:0">

	<input style="width:100%" formaction = "skv_listsectioncontent_refresh.dsp" type="submit"  value="Back" onclick="forceObjectsStatusRefresh()"></input>

	</td>
	<td style="width:10%; border:0">

	<input style="width:100%" type="submit"  value="%ifvar operation equals('edit')% Update %else% Create %endif%" onclick="forceObjectsStatusRefresh()"></input>

	</td>
	<td style="border:0"></td>
	</tr>
	</table>

</fieldset>

</td>
</tr>

</table>


<hr>




</form>	



<br></br>
<br></br>

</body>
</html>
