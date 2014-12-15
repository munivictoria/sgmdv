package com.trascender.catastro.recurso.rfr;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="TGI")
public class DocumentoTGIRfr extends DocHabEspecializadoRfr{

}
