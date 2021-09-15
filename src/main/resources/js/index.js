var IMAGENES = ['1.jpg','demonio.jpg','game_over.png','muerta.jpg'];
var posicionActual = 0;
function retroceder(){
	posicionActual -= 1;
	if(posicionActual<0){
		posicionActual=IMAGENES.length-1;
	}
	renderizar();
	
}
function avanzar(){
	posicionActual += 1;
	if(posicionActual>=IMAGENES.length){
		posicionActual=0;
	}
	renderizar();
	
}
function renderizar(){
	$("#imagen").attr("src",IMAGENES[posicionActual]);
}
function imagenesConseguir(){
	console.log("data");
	$.getJSON("/",function (data) {
			console.log("data2");
			console.log(data);
						
		}
	);
}
$(document).ready(function(){
	
	renderizar();
	imagenesConseguir();
	console.log("sasad");
	
	$("#avanzar").click(function(){
		avanzar();
	});
	$("#retroceder").click(function(){
		retroceder();
	});
});
	
            
        