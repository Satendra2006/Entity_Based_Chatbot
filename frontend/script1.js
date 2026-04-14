const toggleBtn = document.getElementById('toggleBtn');
const radioContainer = document.getElementById('radioContainer');

toggleBtn.addEventListener('click', () => {

  radioContainer.classList.toggle('show');
});
let inp=document.querySelectorAll('input');
// console.log(inp)
let con = document.querySelector('a');
con.addEventListener('click',function(evt){
  // evt.preventDefault();
  const selected = document.querySelector('input[name="category"]:checked');
    if(selected.value=='student'){
      con.href='studentlogin.html';
    }
    if(selected.value=='guest'){
      con.href='guestlogin.html';
    }
    if(selected.value=='faculty'){
      con.href='facultylogin.html';
    }


});