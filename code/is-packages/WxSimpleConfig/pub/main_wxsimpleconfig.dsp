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
</script>
<body>
	<table style="width:100%" style="height:100%">
			<tbody><tr>
			<td class="breadcrumb" colspan="2">WxSimpleConfig &gt; Home </td>
			</tr>
			</tbody>
	</table>

	<fieldset>
		<legend>List of available functionalities</legend>
			<table style="width:100%" style="height:100%" class="tableView">
				<tbody>
					<tr border="0">
						<td style="width:20%; border:1"><h2><a target="_top" href="skv_index.dsp">Variables by Section management</a></h2></td>
						<td style="width:80%; border:1">Manage configuration of variables organized in sections</td>
					</tr>
					<tr border="0">
					</tr>
				</tbody>
			</table>
	</fieldset>
  
<hr>




<br></br>
<br></br>

</body>
</html>
