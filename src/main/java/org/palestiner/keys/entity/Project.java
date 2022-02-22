package org.palestiner.keys.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "PROJECT")
@Entity
public class Project {
    @Id
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    private UUID id;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @InstanceName
    @Column(name = "NAME", nullable = false, unique = true)
    @NotNull(message = "{msg://org.palestiner.keys.entity/KeyInfo.NotNull}")
    private String name;

    @Column(name = "QAS", nullable = false)
    @NotNull(message = "{msg://org.palestiner.keys.entity/KeyInfo.NotNull}")
    @Min(value = 32, message = "{msg://org.palestiner.keys.entity/Project.Min}")
    private String qas;

    @Column(name = "DEV", nullable = false)
    @NotNull(message = "{msg://org.palestiner.keys.entity/KeyInfo.NotNull}")
    @Min(value = 32, message = "{msg://org.palestiner.keys.entity/Project.Min}")
    private String dev;

    @Column(name = "PRD", nullable = false)
    @NotNull(message = "{msg://org.palestiner.keys.entity/KeyInfo.NotNull}")
    @Min(value = 32, message = "{msg://org.palestiner.keys.entity/Project.Min}")
    private String prd;

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQas() {
        return qas;
    }

    public void setQas(String qas) {
        this.qas = qas;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getPrd() {
        return prd;
    }

    public void setPrd(String prd) {
        this.prd = prd;
    }
}
