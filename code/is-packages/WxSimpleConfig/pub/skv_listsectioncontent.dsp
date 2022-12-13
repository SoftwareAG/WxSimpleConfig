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
	return true;
}

function getConfirmation()
{
	var check = confirm("Are you sure you want to proceed?");
	if (check == true) {
		return true;
	}
	else {
		return false;
	}
}
		
</script>
<body>
	<table style="width:100%" style="height:100%">
			<tbody><tr>
			<td class="breadcrumb" colspan="2">WxSimpleConfig &gt; Sections Management &gt; Section Details </td>
			</tr>
			</tbody>
	</table>

<hr>

<table id="OuterRealmStatus" border="2" style="width:100%" style="height:100%" class="tableView">
<tr>
	<td class="heading" colspan=13>List of Variables defined in Section: %value section%</td>
</tr>		  

<tr>
<td>
%invoke wx.simpleConfig.globalVariables.genericContent.skv.web:viewSingleSection%

<table id="skv_list" border="2" style="width:100%" style="height:100%" class="tableView">

<tr class="subheading2">
<td> </td>
<td>Variable Name</td>
<td>Secured</td>
<td>Value</td>
</tr>
%loop configuredValues%
<tr>
<td style="width:5%" ><a target="_self" onclick="return getConfirmation()" href="/invoke/wx.simpleConfig.globalVariables.genericContent.skv.web/removeSkv?section=%value section%&key=%value key%&landingPage=/WxSimpleConfig/skv_listsectioncontent.dsp?section=%value section%" title="Delete"><img src="images/Delete_Icon_small.png" /></a></td>

<td style="width:40%">
<!-- <a href="/WmRoot/settings-global-variables-addedit.dsp?operation=edit&key=%value section%_%value key%">%value key%</a> -->
<a href="skv_neweditskv.dsp?operation=edit&section=%value section%&key=%value key%">%value key%</a>
</td>

<td>
%value isSecure%
</td>

<td style="width:45%">
%ifvar isSecure equals('true')%
******
%else%
%value value%
%endif%
</td>


</tr>
%endloop%
</table>

%endinvoke%
</td>
</tr>

</table>


<hr>



<fieldset id="Actions">
	<legend >Actions</legend>

	<table style="border:0">
	
	<tr>
	<td style="width:10%; border:0">

	<form action = "skv_listsections.dsp">
		<input style="width:100%" type="submit"  value="Back" onclick="forceObjectsStatusRefresh()"></input>
	</form>

	</td>
	<td style="width:10%; border:0">

	<form action = "skv_listsectioncontent_refresh.dsp">
	    <input hidden name="section" value="%value section%">
		<input style="width:100%" type="submit"  value="Refresh Section %value section%" onclick="forceObjectsStatusRefresh()"></input>
	</form>

	</td>
	<td style="width:10%; border:0">

	<form action = "skv_neweditskv.dsp">
	    <input hidden name="section" value="%value section%">
	    <input hidden name="operation" value="add">
		<input style="width:100%" type="submit"  value="Define new variable" onclick="forceObjectsStatusRefresh()"></input>
	</form>

	</td>
	<td style="border:0"></td>
	</tr>
	</table>

</fieldset>




<br></br>
<br></br>

</body>
</html>
