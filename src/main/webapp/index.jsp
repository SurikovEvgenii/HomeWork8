<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Calculate</title>
    <link type="text/css" href="style.css" rel="stylesheet">
</head>
<body>

<br/>

<p><b>Max symbol sequence:</b> ${seqSymbolAttr}</p>
<p>You can paste your text and replace text in 'text.txt'</p>
<br>

    <form method="POST" action="text-servlet">
        <label for="text-aria" style="font-family: 'Arial Black'">Paste text in aria</label><br><br>
        <textarea id="text-aria" name="story" rows="10" cols="50"></textarea><br>
        <button class="glow-on-hover" type="submit">
            Send text
        </button>
    </form>
<br>
<br>
<button class="glow-on-hover" type="button">
    <a href="text-servlet" style="text-decoration: none; color: white" >Calculate count symbol from 'text.txt' in resources:</a>
</button>


</body>
</html>