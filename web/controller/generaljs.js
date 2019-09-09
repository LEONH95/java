
var nuevoUsuario = false;
var dirWS = "http://localhost:8080/WebServices/webresources/generic";
window.onload = function () {



    $(".nuevo").hide();
    consultarUsuarios();


};

function consultarUsuarios() {
    $.ajax({

        type: 'GET',
        url: dirWS + '/consultarUsuarios',
        dataType: 'json',
        success: function (data) {

            for (var i = 0; i < data.length; i++) {

                generarNodoUsuario(data[i].idUsuario, data[i].nombre, data[i].apellidos, data[i].fechaNacimiento);

            }

        }

    });
}

function ajax() {
    var nombre = "Melisa";
    var apellido = "Espinoza";
    var fecha = "1996-03-30";

    $.ajax({
        type: 'GET',
        url: dirWS + '/insertarUsuario/' + nombre + '/' + apellido + '/' + fecha,
        dataType: 'json',
        success: function (data) {
            console.log(data);
        }

    });
}

function generarNodoUsuario(id, nombre, apellidos, fecha) {

    var usuario = document.createElement("div");
    usuario.classList.add("usuario");

    var idUsuario = document.createElement("div");
    idUsuario.classList.add("idUsuario");

    var nombreUsuario = document.createElement("div");
    nombreUsuario.classList.add("nombreUsuario");

    idUsuario.appendChild(nombreUsuario);
    usuario.appendChild(idUsuario);

    usuario.data = {

        id: id,
        nombre: nombre,
        apellidos: apellidos,
        fechaN: fecha
    };

    var texto = document.createTextNode(usuario.data.id);
    idUsuario.appendChild(texto);
    texto = document.createTextNode(usuario.data.nombre);
    nombreUsuario.appendChild(texto);



    usuario.onclick = function () {


        mostrarDatos(this.data);
    };


    document.getElementById("col-izq").appendChild(usuario);


}

function mostrarDatos(datosUsuario) {
    $("#borrarUsuario").show();
    $(".editar").show();
    var botonE = document.getElementById("editarUsuario");
    botonE.data = {
        id: datosUsuario.id
    };

    document.getElementById("idUsuario").innerHTML = datosUsuario.id;
    document.getElementById("nombreUsuario").innerHTML = datosUsuario.nombre;
    document.getElementById("apellidosUsuario").innerHTML = datosUsuario.apellidos;
    document.getElementById("fechaNacimiento").innerHTML = datosUsuario.fechaN;
}

function crearNuevo() {
        
        document.getElementById("nombreNvUsuario").value = null;
        document.getElementById("apellidosNvUsuario").value = null;
        document.getElementById("fechaNacimientoNvUsuario").value = null;
    
    if (this.nuevoUsuario) {
        $(".info").show();
        $(".nuevo").hide();
        $(".editar").hide();
        
        
        this.nuevoUsuario = false;
        $("#btnNuevo").text("Nuevo");
    } else {
        $(".info").hide();
        $(".nuevo").show();
        this.nuevoUsuario = true;
        $("#btnNuevo").text("Regresar");
        $("#editarUsuario").hide();
    }
    $('#mensajes').html('');
}

function agregarUsuario() {
    var mensaje = $("#mensajes");
    var nombre = document.getElementById("nombreNvUsuario").value;
    var apellidos = document.getElementById("apellidosNvUsuario").value;
    var fechaN = document.getElementById("fechaNacimientoNvUsuario").value;


    if (nombre !== "" && nombre.search('[a-zA-Z ]+') !== -1) {
        if (apellidos !== "" && apellidos.search('[a-zA-Z ]+') !== -1) {
            if (fechaN !== "" && fechaN.search('[0-9]{4}-[0-9]{2}-[0-9]{2}') !== -1) {


                $.ajax({
                    type: 'GET',
                    url: dirWS + '/insertarUsuario/' + nombre + '/' + apellidos + '/' + fechaN,
                    dataType: 'json',
                    success: function (data) {

                        document.getElementById("nombreNvUsuario").value = "";
                        document.getElementById("apellidosNvUsuario").value = "";
                        document.getElementById("fechaNacimientoNvUsuario").value = "";
                        location.reload();
                    }

                });

            } else {
                mensaje.html('La fecha debe ser escrita como aaaa-mm-dd');
            }

        } else {
            mensaje.html('El nombre debe ser solo letras ');
        }

    } else {
        mensaje.html('El id debe ser numeros');
    }

}
function editarUsuario() {
    $(".info").hide();
    $(".nuevo").show();
    $("#btnNuevo").hide();
    $("#nuevoUsuario").hide();
    $("#actualizarUsuario").show();
    $("#editarUsuario").hide();
    $("#cancelarCambios").show();
    document.getElementById("nombreNvUsuario").value = $("#nombreUsuario").html();
    document.getElementById("apellidosNvUsuario").value = $("#apellidosUsuario").html();
    document.getElementById("fechaNacimientoNvUsuario").value = $("#fechaNacimiento").html();

}

function cancelarCambios() {
   
    $(".info").show();
    $(".nuevo").hide();
    $("#btnNuevo").show ();
    $("#actualizarUsuario").hide();
    $("#editarUsuario").show();
    $("#cancelarCambios").hide();
}

function actualizarUsuario(){
    var idEdit = document.getElementById("editarUsuario").data.id;
    var nombre = document.getElementById("nombreNvUsuario").value;
    var apellidos = document.getElementById("apellidosNvUsuario").value;
    var fecha = document.getElementById("fechaNacimientoNvUsuario").value;
    var mensaje = $("#mensajes");
    if (nombre !== "" && nombre.search('[a-zA-Z ]+') !== -1) {
        if (apellidos !== "" && nombre.search('[a-zA-Z ]+') !== -1) {
            if (fecha !== "" && fecha.search('[0-9]{4}-[0-9]{2}-[0-9]{2}') !== -1) {
                
                
                $.ajax({
                    
                    type: 'GET',
                    url: dirWS + '/actualizarUsuarios/'+idEdit+'/'+nombre+'/'+apellidos+'/'+fecha,
                    dataType: 'json',
                    success: function (data){
                       
                        mensaje.html('El registro se actualizo '+ data);
                         location.reload();
                    }
                        
                });
            }else{
                mensaje.html('La fecha debe ser escrita como aaaa-mm-dd');
            }
        }else{
            mensaje.html('El nombre debe ser solo letras ');
        }
    }else{
        mensaje.html('El id debe ser numeros');
    }
}

function borrarUsuario(){
    
    $(".borrar").show();
    $("#borrarUsuario").hide();
    
}

function borrarUsuario(){
    
    $(".borrar").show();
    $("#borrarUsuario").hide();
    $(".editar").hide();
    
}

function siBorrarUsuario(){
    
    var idEdit = document.getElementById("editarUsuario").data.id;
            $.ajax({
                    
                    type: 'GET',
                    url: dirWS + '/borrarUsuario/'+idEdit,
                    dataType: 'json',
                    success: function (data){
                       
                        location.reload();
                         
                    }
                        
                });
    
}

function noBorrarUsuario(){
    
    location.reload();
    
}









