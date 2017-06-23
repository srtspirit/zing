/* Remember Me */
function newCookie(name,value,days) {
 var days = 14;   // the number at the left reflects the number of days for the cookie to last
                 // modify it according to your needs
 if (days) {
   var date = new Date();
   date.setTime(date.getTime()+(days*24*60*60*1000));
   var expires = "; expires="+date.toGMTString(); 
   document.cookie = name+"="+value+expires+";  path=/"; 
  }
   else {
	   var expires = "";
	   document.cookie = name+"="+value+expires+";  path=/"; 
   }
 	
 }

function readCookie(name) {
   var nameSG = name + "=";
   var nuller = '';
  if (document.cookie.indexOf(nameSG) == -1)
    return nuller;

   var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0)==' ') c = c.substring(1,c.length);
  if (c.indexOf(nameSG) == 0) return c.substring(nameSG.length,c.length); }
    return null; 
}

function toMem() 
{
    newCookie('un', document.getElementById("login_username").value); 
    newCookie('pwd', document.getElementById("login_password").value);   // field you wish to have the script remember
}

function remCookie() {
	document.getElementById("login_username").value = readCookie("un");
	document.getElementById("login_password").value = readCookie("pwd");
	
}

// Multiple onload function created by: Simon Willison
// simon.incutio.com/archive/2004/05/26/addLoadEvent
function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    };
  }
}

addLoadEvent(function() {
  remCookie();
});