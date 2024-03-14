<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilos.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-table.min.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<!-- Botoness -->
    
<section class="py-5 px-5">
	<div class="container">
	<h1>Facturacion</h1>
			<div class="container" style="text-align: center;">
				<button class="btn btn-primary" id="mostrar-cliente">
					<i class="fa-solid fa-user-plus"></i>
				</button>
				<button class="btn btn-primary" id="mostrar-pedido">
					<i class="fa-solid fa-basket-shopping"></i>
				</button>
				<button class="btn btn-primary" id="mostrar-formapago">
					<i class="fa-regular fa-credit-card"></i>
				</button>
				<button class="btn btn-primary" id="mostrar-empresaProducto">
					<i class="fa-solid fa-building"></i>
				</button>
				<button class="btn btn-primary" id="mostrar-producto">
					<i class="fa-solid fa-list"></i>
				</button
			</div>
	<dialog id="alert-dialog-clientes">
	<form action="findOneCliente" id="formCliente" name="formCliente" method="get" >			
			<div class="table-responsive">
				<table id="tabla1"
					   name="tabla1"
					   data-height="600"
					   data-search="true"
					   data-pagination="true"
					   data-toogle="tabla1"
					   data-toolbar=".toolbar"
					   class="table table-striped table-sm"
						>
						<thead>
						<tr>
							<th data-field="Acciones" data-sortable="true" >Acciones</th>
							<th data-field="N°" data-sortable="true" >idCliente</th>
							<th data-field="Cédula" data-sortable="true" >cedula</th>
							<th data-field="Nombre" data-sortable="true">nombre</th>
							<th data-field="Apellido" data-sortable="true">apellido</th>
							<th data-field="Dirección" data-sortable="true">direccion</th>
							<th data-field="Teléfono" data-sortable="true">telefono</th>
							<th data-field="Correo" data-sortable="true">correo</th>
							<th data-field="Genero" data-sortable="true">Genero</th>
							<th data-field="Fecha Nacimiento" data-sortable="true">Fecha de Nacimiento</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${clientes}">
								<tr>
									<td>
										<div class="container-fluid btn-group" role="group" >
											<button class="btn btn-success" onclick="window.location.href='/ismac-emprendimientos-web/facturacion/findOneCliente?idCliente=${item.idCliente}&opcion=1'; return false;">
												<i class="fa-regular fa-square-plus"></i>
											</button>
										</div>
									</td>
									<td>${item.idCliente}</td>
							        <td>${item.cedula}</td>
							        <td>${item.nombre}</td>
							        <td>${item.apellido}</td>
							        <td>${item.direccion}</td>
							        <td>${item.telefono}</td>
							        <td>${item.correo}</td>
							        <td>${item.genero}</td>
							        <td>${fn:substring(item.fechadenacimiento,0,10)}</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</form>
		<div class="container-fluid btn-group" role="group" >
			<button class="btn btn-success" onClick="this.parentElement.Close()">
			    <i class="fa-solid fa-check-to-slot"></i>
			</button>
		</div>
		</dialog>
	</div>
	
	
	<div class="container">
	<dialog id="alert-dialog-pedidos">
	<form action="findOnePedido" id="formPedido" name="formPedido" method="get" >			
			<div class="table-responsive">
				<table id="tabla1"
					   name="tabla1"
					   data-height="600"
					   data-search="true"
					   data-pagination="true"
					   data-toogle="tabla1"
					   data-toolbar=".toolbar"
					   class="table table-striped table-sm"
						>
						<thead>
						<tr>
							<th data-field="Acciones" data-sortable="true" >Acciones</th>
							<th data-field="N°" data-sortable="true" >ID Pedido</th>
							<th data-field="N° Pedido" data-sortable="true" >Numero de Pedido</th>
							<th data-field="Confirmación" data-sortable="true">Confirmacion de Pedido</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${pedidos}">
								<tr>
									<td>
										<div class="container-fluid btn-group" role="group" >
											<button class="btn btn-success" onclick="window.location.href='/ismac-emprendimientos-web/facturacion/findOnePedido?idPedido=${item.idPedido}&opcion=2'; return false;">
												<i class="fa-regular fa-square-plus"></i>
											</button>
										</div>
									</td>
									<td>${item.idPedido}</td>
									<td>${item.numPedido}</td>
									<td>${item.confirmacionPedido}</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</form>
		<div class="container-fluid btn-group" role="group" >
			<button class="btn btn-success" onClick="this.parentElement.Close()">
			    <i class="fa-solid fa-check-to-slot"></i>
			</button>
		</div>
		</dialog>
	</div>
	
	<div class="container">
	<dialog id="alert-dialog-formapagos">
	<form action="findOneFormaPago" id="formFormaPago" name="formFormaPago" method="get" >			
			<div class="table-responsive">
				<table id="tabla1"
					   name="tabla1"
					   data-height="600"
					   data-search="true"
					   data-pagination="true"
					   data-toogle="tabla1"
					   data-toolbar=".toolbar"
					   class="table table-striped table-sm"
						>
						<thead>
						<tr>
							<th data-field="Acciones" data-sortable="true" >Acciones</th>
							<th data-field="N°" data-sortable="true" >ID Forma de Pago</th>
							<th data-field="Forma Pago" data-sortable="true" >Forma Pago</th>
							<th data-field="Descripción" data-sortable="true">Descripcion</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${formapagos}">
								<tr>
									<td>
										<div class="container-fluid btn-group" role="group" >
											<button class="btn btn-success" onclick="window.location.href='/ismac-emprendimientos-web/facturacion/findOneFormaPago?idFormaPago=${item.idFormaPago}&opcion=1'; return false;">
												<i class="fa-regular fa-square-plus"></i>
											</button>
										</div>
									</td>
									<td>${item.idFormaPago}</td>
									<td>${item.formaspago}</td>
									<td>${item.descripcion}</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</form>
		<div class="container-fluid btn-group" role="group" >
			<button class="btn btn-success" onClick="this.parentElement.Close()">
			    <i class="fa-solid fa-check-to-slot"></i>
			</button>
		</div>
		</dialog>
	</div>
	
	<div class="container">
	<dialog id="alert-dialog-productos">
	<form action="findOneProducto" id="formProducto" name="formProducto" method="get" >			
			<div class="table-responsive">
				<table id="tabla1"
					   name="tabla1"
					   data-height="600"
					   data-search="true"
					   data-pagination="true"
					   data-toogle="tabla1"
					   data-toolbar=".toolbar"
					   class="table table-striped table-sm"
						>
						<thead>
						<tr>
							<th data-field="Acciones" data-sortable="true" >Acciones</th>
							<th data-field="N°" data-sortable="true" >ID Producto</th>
							<th data-field="N° Producto" data-sortable="true" >Numero Producto</th>
							<th data-field="Descripción" data-sortable="true">Descripción</th>
							<th data-field="Precio" data-sortable="true">Precio</th>
							<th data-field="Stock" data-sortable="true">Stock</th>
							<th data-field="Img Producto" data-sortable="true">ImgProducto</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${productos}">
								<tr>
									<td>
										<div class="container-fluid btn-group" role="group" >
											<button class="btn btn-success" onclick="window.location.href='/ismac-emprendimientos-web/facturacion/findOneProducto?idProducto=${item.idProducto}&opcion=1'; return false;">
												<i class="fa-regular fa-square-plus"></i>
											</button>
										</div>
									</td>
				                    <td>${item.idProducto}</td>
									<td>${item.numeroProducto}</td>
									<td>${item.descripcion}</td>
									<td>${item.precioProducto}</td>
									<td>${item.stock}</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</form>
		<div class="container-fluid btn-group" role="group" >
			<button class="btn btn-success" onClick="this.parentElement.Close()">
			    <i class="fa-solid fa-check-to-slot"></i>
			</button>
		</div>
		</dialog>
	</div>
	
	<div class="container">
	<dialog id="alert-dialog-EmpresaProductos">
	<form action="findOneEmpresaProducto" id="formEmpresaProducto" name="formEmpresaProducto" method="get" >			
			<div class="table-responsive">
				<table id="tabla1"
					   name="tabla1"
					   data-height="600"
					   data-search="true"
					   data-pagination="true"
					   data-toogle="tabla1"
					   data-toolbar=".toolbar"
					   class="table table-striped table-sm"
						>
						<thead>
						<tr>
							<th data-field="Acciones" data-sortable="true" >Acciones</th>
							<th data-field="N°" data-sortable="true" >ID Empresa Producto</th>
							<th data-field="Producto" data-sortable="true" >Producto</th>
							<th data-field="Descripción" data-sortable="true">Descripción</th>
							<th data-field="Empresa" data-sortable="true">Empresa</th>
							<th data-field="Producto" data-sortable="true">Producto</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${empresa_productos}">
								<tr>
									<td>
										<div class="container-fluid btn-group" role="group" >
											<button class="btn btn-success" onclick="window.location.href='/ismac-emprendimientos-web/facturacion/findOneEmpresaProducto?idempresa_producto=${item.idempresa_producto}&opcion=1'; return false;">
												<i class="fa-regular fa-square-plus"></i>
											</button>
										</div>
									</td>
									<td>${item.idempresa_producto}</td>
									<td>${item.producto}</td>
									<td>${item.descripcion}</td>
									<td>${item.idempresa.nombreempresa}</td>
									<td>${item.idproducto.numeroProducto}</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
		</form>
		<div class="container-fluid btn-group" role="group" >
			<button class="btn btn-success" onClick="this.parentElement.Close()">
			    <i class="fa-solid fa-check-to-slot"></i>
			</button>
		</div>
		</dialog>
	</div>	

</section>

<section class="py-5 px-5">
	<div class="container">
<form action="addDetalle" id="formDetalles" name="formDetalles" method="post" class="row g-3">
    <div class="col-md-3">
        <label for="numFactura" class="form-label">Número de Factura</label>
        <input type="text" class="form-control" id="numFactura" name="numFactura" value="${numFactura}" disabled>
    </div>
    <div class="col-md-3">
        <label for="fechaActual" class="form-label">Fecha</label>
        <input type="date" class="form-control" id="fechaActual" name="fechaActual" value="${fn:substring(fechaActual,0,10)}" disabled>
    </div>
    <div class="col-md-3">
        <label for="totalNeto" class="form-label">TOTAL NETO</label>
        <input type="number" step="any" class="form-control" id="totalNeto" name="totalNeto" value="${totalNeto}" placeholder="00.00" disabled>
    </div>
    <div class="col-md-3">
        <label for="iva" class="form-label">IVA</label>
        <input type="number" step="any" class="form-control" id="iva" name="iva" value="${iva}" placeholder="00.00" disabled>
    </div>
    <div class="col-md-3">
        <label for="total" class="form-label">TOTAL</label>
        <input type="number" step="any" class="form-control" id="total" name="total" value="${total}" placeholder="00.00" disabled>
    </div>
    <div class="col-12 d-flex justify-content-end">
        <button type="submit" class="btn btn-primary" onclick="${pageContext.request.contextPath}/facturacion/addDetalle?list=${list}">
        <i class="fa-solid fa-basket-shopping"></i>
        </button>
    </div>
    
    <h3>Cliente</h3>
<div class="row g-3">
    <div class="col-md-3">
        <label for="cedula" class="form-label">Cédula</label>
        <input type="hidden" id="idCliente" name="idCliente" value="${cliente.idCliente}" />
        <input type="text" class="form-control" id="cedula" name="cedula" value="${cliente.cedula}" disabled>
    </div>
    <div class="col-md-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" disabled>
    </div>
    <div class="col-md-3">
        <label for="apellido" class="form-label">Apellido</label>
        <input type="text" class="form-control" id="apellido" name="apellido" value="${cliente.apellido}" disabled>
    </div>
    <div class="col-md-3">
        <label for="direccion" class="form-label">Dirección</label>
        <input type="text" class="form-control" id="direccion" name="direccion" value="${cliente.direccion}" disabled>
    </div>
    <div class="col-md-3">
        <label for="telefono" class="form-label">Teléfono</label>
        <input type="text" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}" disabled>
    </div>
    <div class="col-md-3">
        <label for="correo" class="form-label">Correo</label>
        <input type="email" class="form-control" id="correo" name="correo" value="${cliente.correo}" disabled>
    </div>
</div>

<h3>Pedidos</h3>
<div class="row g-3">
    <div class="col-md-3">
        <label for="numPedido" class="form-label">Número</label>
        <input type="hidden" id="idPedido" name="idPedido" value="${pedido.idPedido}" />
        <input type="text" class="form-control" id="numPedido" name="numPedido" value="${pedido.numPedido}" disabled>
    </div>
    <div class="col-md-3">
        <label for="confirmacionPedido" class="form-label">Confirmación</label>
        <input type="text" class="form-control" id="confirmacionPedido" name="confirmacionPedido" value="${pedido.confirmacionPedido}" disabled>
    </div>
</div>

<h3>Forma Pago</h3>
<div class="row g-3">
    <div class="col-md-3">
        <label for="formaspago" class="form-label">Forma Pago</label>
        <input type="hidden" id="idFormaPago" name="idFormaPago" value="${formapago.idFormaPago}" />
        <input type="text" class="form-control" id="formaspago" name="formaspago" value="${formapago.formaspago}" disabled>
    </div>
    <div class="col-md-3">
        <label for="descripcion" class="form-label">Descripción</label>
        <input type="text" class="form-control" id="descripcion" name="descripcion" value="${formapago.descripcion}" disabled>
    </div>
</div>
</form>
	</div>	

</section>



<!-- Codigos script -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
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
	const mostrarEmpresaProducto = document.querySelector("#mostrar-empresaProducto"); 
	mostrarEmpresaProducto.addEventListener("click", function () {
		const alertDialogEmpresaProductos = document.querySelector("#alert-dialog-EmpresaProductos");
		alertDialogEmpresaProductos.showModal();
	});
	const mostrarProducto = document.querySelector("#mostrar-producto"); 
	mostrarProducto.addEventListener("click", function () {
		const alertDialogProductos = document.querySelector("#alert-dialog-productos");
		alertDialogProductos.showModal();
	});
	</script>
		<script>
		var $tabla1 = $('#tabla1')
		
			$(function(){
				$tabla1.bootstrapTable({
					sortReset: true
				})
			})
		
	</script>
</body>
</html>