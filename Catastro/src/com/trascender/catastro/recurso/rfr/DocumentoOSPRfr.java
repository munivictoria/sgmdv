package com.trascender.catastro.recurso.rfr;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="OSP")
public class DocumentoOSPRfr extends DocHabEspecializadoRfr{

}
