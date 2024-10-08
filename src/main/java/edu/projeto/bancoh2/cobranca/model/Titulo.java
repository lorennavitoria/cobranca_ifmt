package edu.projeto.bancoh2.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;



import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import edu.projeto.bancoh2.cobranca.model.enuns.StatusTitulo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;


@Entity
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String descricao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@NotNull(message = "O valor não pode ser nulo")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusTitulo getStatus() {
		return status;
	}

	public void setStatus(StatusTitulo status) {
		this.status = status;
	}

	public boolean isPendente() {
		return StatusTitulo.PENDENTE.equals(this.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
