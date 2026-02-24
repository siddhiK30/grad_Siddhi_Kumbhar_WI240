<h1>Spring Data JPA Demo</h1>
<h2>Employee Management Application</h2>
<hr>
<h3>

<form method="post" action="addEmp">
    Emp ID : <input type="number" name="eid"><br>
    Name : <input type="text" name="name"><br>
    Age : <input type="number" name="age"><br>
    Salary : <input type="number" name="salary"><br>
    Designation : <input type="text" name="deisgnation"><br>
    <br>
    <input type="submit" value="INSERT">
    <input type="reset" value="RESET">
</form>

 <h3> Search Employee</h3>
 <form method="post" action="searchEmp">
     Emp ID : <input type="number" name="eid"><br>
    
     <input type="submit" value="SEARCH">
     <input type="reset" value="RESET">
 </form>
 
 <h3> Update Employee</h3>

 <form method="post" action="updEmp">
     Emp ID : <input type="number" name="eid"><br>
     Name : <input type="text" name="name"><br>
     Age : <input type="number" name="age"><br>
     Salary : <input type="number" name="salary"><br>
     Designation : <input type="text" name="deisgnation"><br>
     <br>
     <input type="submit" value="UPDATE">
     <input type="reset" value="RESET">
 </form>
 <h3> Delete Employee</h3>
  <form method="post" action="delEmp">
      Emp ID : <input type="number" name="eid"><br>
     
      <input type="submit" value="DELETE">
      <input type="reset" value="RESET">
  </form>
  <h3> Search  Employee by Deisgnaion</h3>
    <form method="post" action="desgEmp">
		Designation : <input type="text" name="deisgnation"><br>
       
        <input type="submit" value="SEARCH">
        <input type="reset" value="RESET">
    </form>
    
	<h3> Search  Above by Given Age</h3>
	  <form method="post" action="byAge">
		Age : <input type="number" name="age"><br>

	      <input type="submit" value="SEARCH">
	      <input type="reset" value="RESET">
	  </form>
	  
	  <h3> Search  Employee by Deisgnaion Sorted</h3>
	    <form method="post" action="desgSort">
	  	Designation : <input type="text" name="deisgnation"><br>
	       
	        <input type="submit" value="SEARCH">
	        <input type="reset" value="RESET">
	    </form>
</h3>
</body>
</html>
