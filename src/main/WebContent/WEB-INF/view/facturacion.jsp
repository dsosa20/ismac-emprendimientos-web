<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Facturacion</h1>
	<button id="mostrar-cliente">Agregar Cliente</button>
	<button id="mostrar-pedido">Agregar Pedido</button>
	<button id="mostrar-formapago">Agregar Forma de pago</button>
	<button id="mostrar-producto">Agregar Producto</button>
	<button id="mostrar-empresaProducto">Agregar Empresa Producto</button>
	
	<textarea >${empresa_productosAgregados}</textarea>
	<textarea >${productosAgregados}</textarea>
	
	<div>
		<dialog id="alert-dialog-clientes">
			<form action="findOneCliente" id="formCliente" name="formCliente" method="get" >
				<table>
					<thead>
						<tr>
							<th>Accion</th>
							<th>Id Cliente</th>
							<th>Cedula</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Direccion</th>
							<th>Telefono</th>
							<th>Correo</th>
							<th>Genero</th>
							<th>Fecha de Nacimiento</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${clientes}">
							<tr>
							<td>
								<button><a href="${pageContext.request.contextPath}/facturacion/findOneCliente?idCliente=${item.idCliente}">Agregar</a></button>							
							</td>
					        <td>${item.idCliente}</td>
					        <td>${item.cedula}</td>
					        <td>${item.nombre}</td>
					        <td>${item.apellido}</td>
					        <td>${item.direccion}</td>
					        <td>${item.telefono}</td>
					        <td>${item.correo}</td>
					        <td>${item.genero}</td>
					        <td>${item.fechadenacimiento}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>
	
	
	<div>
		<dialog id="alert-dialog-pedidos">
			<form action="findOnePedido" id="formPedido" name="formPedido" method="get" >
				<table>
					<thead>
						<tr>
							<th>Accion</th>
							<th>ID Pedido</th>
							<th>Numero de Pedido</th>
							<th>Fecha del pedido</th>
							<th>Confirmacion de Pedido</th>
							<th>Cliente</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${pedidos}">
							<tr>
								<td>
									<button><a href="${pageContext.request.contextPath}/facturacion/findOnePedido?idPedido=${item.idPedido}">Agregar</a></button>
								</td>
								<td>${item.idPedido}</td>
								<td>${item.numPedido}</td>
								<td>${item.fechapedido}</td>
								<td>${item.confirmacionPedido}</td>
								<td>${item.cliente.cedula} ${item.cliente.nombre}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>
	
	
	<div>
		<dialog id="alert-dialog-formapagos">
			<form action="findOneFormaPago" id="formFormaPago" name="formFormaPago" method="get" >
				<table>
					<thead>
						<tr>
							<th>Accion</th>
							<th>ID Forma de Pago</th>
							<th>Forma Pago</th>
							<th>Descripcion</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${formapagos}">
							<tr>
								<td>
									<button><a href="${pageContext.request.contextPath}/facturacion/findOneFormaPago?idFormaPago=${item.idFormaPago}">Agregar</a></button>
								</td>
								<td>${item.idFormaPago}</td>
								<td>${item.formaspago}</td>
								<td>${item.descripcion}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
				<button onClick="this.parentElement.close()">Aceptar</button>
		</dialog>
	</div>

	
	
	
	
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.js"></script>
	<script>
		
	const mostrarCliente = document.querySelector("#mostrar-cliente"); 
	mostrarCliente.addEventListener("click", function () {
		const alertDialogClientes = document.querySelector("#alert-dialog-clientes");
		alertDialogClientes.showModal();
	});
	
	const mostrarPedido = document.querySelector("#mostrar-pedido"); 
	mostrarPedido.addEventListener("click", function () {
		const alertDialogPedidos = document.querySelector("#alert-dialog-pedidos");
		alertDialogPedidos.showModal();
	});
	
	const mostrarFormaPago = document.querySelector("#mostrar-formapago"); 
	mostrarFormaPago.addEventListener("click", function () {
		const alertDialogFormapagos = document.querySelector("#alert-dialog-formapagos");
		alertDialogFormapagos.showModal();
	});
	
	const mostrarProducto = document.querySelector("#mostrar-producto"); 
	mostrarProducto.addEventListener("click", function () {
		const alertDialogProductos = document.querySelector("#alert-dialog-productos");
		alertDialogProductos.showModal();
	});
	
	const mostrarEmpresaProducto = document.querySelector("#mostrar-empresaProducto"); 
	mostrarEmpresaProducto.addEventListener("click", function () {
		const alertDialogEmpresaProductos = document.querySelector("#alert-dialog-EmpresaProductos");
		alertDialogEmpresaProductos.showModal();
	});
	
	</script>
	
</body>
</html>