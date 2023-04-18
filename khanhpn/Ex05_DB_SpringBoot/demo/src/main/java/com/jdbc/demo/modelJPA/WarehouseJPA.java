package com.jdbc.demo.modelJPA;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "warehouse")
@ToString
public class WarehouseJPA implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warehouse_id")
	private long warehouseId;
	@Column(name = "warehouse_code")
	private String warehouseCode;
	@Column(name = "warehouse_name")
	private String warehouseName;
	@Column(name = "location")
	private String location;
	@Column(name = "created_date")
	private Timestamp createDate;
	@Column(name = "updated_date")
	private Timestamp updateDate;

	public WarehouseJPA() {
	}

	public WarehouseJPA(String warehouseCode, String warehouseName, String location, Timestamp createDate,
			Timestamp updateDate) {
		this.warehouseCode = warehouseCode;
		this.warehouseName = warehouseName;
		this.location = location;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

}
