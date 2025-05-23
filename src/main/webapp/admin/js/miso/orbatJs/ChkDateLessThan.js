function checkdate(obj,validateDate) {
	if(validateDate.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = validateDate.value;
		if ((Date.parse(myDate) < Date.parse(Date1))) {
			alert('You cannot select less than From Date.');
			obj.value = "";
		}
	}
	else
	{
			alert("Please Select From Date first.");
			obj.value = "";
	}
}