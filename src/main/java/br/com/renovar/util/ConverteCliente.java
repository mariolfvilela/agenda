package br.com.renovar.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.renovar.modelo.Cliente;
import br.com.renovar.rn.ClienteRN;

@FacesConverter(value="converteCliente" )
public class ConverteCliente implements Converter{
	private boolean primera=false;
	private Integer cod=0;
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			ClienteRN clienteRN = new ClienteRN();
			Cliente cliente=new Cliente();
			if(cod==codigo){
				return cliente;
			}
			cod=codigo;
			try {
				
				cliente=clienteRN.carregar(codigo);
				return cliente;
			} catch (Exception e) {
				throw new ConverterException("Não foi possóvel encontrar a categoria de código, problema no converterPessoa " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return String.valueOf(cliente.getPesId());
		}
		return "";
	}

	public boolean isPrimera() {
		return primera;
	}

	public void setPrimera(boolean primera) {
		this.primera = primera;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}
	
}
