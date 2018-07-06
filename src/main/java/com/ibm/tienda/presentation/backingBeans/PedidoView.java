package com.ibm.tienda.presentation.backingBeans;

import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.PedidoDTO;
import com.ibm.tienda.presentation.businessDelegate.*;
import com.ibm.tienda.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PedidoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PedidoView.class);
    private InputText txtCodigo;
    private InputText txtDireccion;
    private InputText txtTotal;
    private InputText txtId_Usuario;
    private InputText txtId;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaPedido;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PedidoDTO> data;
    private PedidoDTO selectedPedido;
    private Pedido entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PedidoView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPedido = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPedido = null;

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(true);
        }

        if (txtDireccion != null) {
            txtDireccion.setValue(null);
            txtDireccion.setDisabled(true);
        }

        if (txtTotal != null) {
            txtTotal.setValue(null);
            txtTotal.setDisabled(true);
        }

        if (txtId_Usuario != null) {
            txtId_Usuario.setValue(null);
            txtId_Usuario.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaPedido != null) {
            txtFechaPedido.setValue(null);
            txtFechaPedido.setDisabled(true);
        }

        if (txtId != null) {
            txtId.setValue(null);
            txtId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaPedido() {
        Date inputDate = (Date) txtFechaPedido.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = (id != null) ? businessDelegatorView.getPedido(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigo.setDisabled(false);
            txtDireccion.setDisabled(false);
            txtTotal.setDisabled(false);
            txtId_Usuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaPedido.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(false);
            txtDireccion.setValue(entity.getDireccion());
            txtDireccion.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaPedido.setValue(entity.getFechaPedido());
            txtFechaPedido.setDisabled(false);
            txtTotal.setValue(entity.getTotal());
            txtTotal.setDisabled(false);
            txtId_Usuario.setValue(entity.getUsuario().getId());
            txtId_Usuario.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPedido = (PedidoDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPedido"));
        txtCodigo.setValue(selectedPedido.getCodigo());
        txtCodigo.setDisabled(false);
        txtDireccion.setValue(selectedPedido.getDireccion());
        txtDireccion.setDisabled(false);
        txtFechaCreacion.setValue(selectedPedido.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaPedido.setValue(selectedPedido.getFechaPedido());
        txtFechaPedido.setDisabled(false);
        txtTotal.setValue(selectedPedido.getTotal());
        txtTotal.setDisabled(false);
        txtId_Usuario.setValue(selectedPedido.getId_Usuario());
        txtId_Usuario.setDisabled(false);
        txtId.setValue(selectedPedido.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPedido == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Pedido();

            Long id = FacesUtils.checkLong(txtId);

            entity.setCodigo(FacesUtils.checkString(txtCodigo));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaPedido(FacesUtils.checkDate(txtFechaPedido));
            entity.setId(id);
            entity.setTotal(FacesUtils.checkDouble(txtTotal));
            entity.setUsuario((FacesUtils.checkLong(txtId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtId_Usuario)) : null);
            businessDelegatorView.savePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long id = new Long(selectedPedido.getId());
                entity = businessDelegatorView.getPedido(id);
            }

            entity.setCodigo(FacesUtils.checkString(txtCodigo));
            entity.setDireccion(FacesUtils.checkString(txtDireccion));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaPedido(FacesUtils.checkDate(txtFechaPedido));
            entity.setTotal(FacesUtils.checkDouble(txtTotal));
            entity.setUsuario((FacesUtils.checkLong(txtId_Usuario) != null)
                ? businessDelegatorView.getUsuario(FacesUtils.checkLong(
                        txtId_Usuario)) : null);
            businessDelegatorView.updatePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPedido = (PedidoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPedido"));

            Long id = new Long(selectedPedido.getId());
            entity = businessDelegatorView.getPedido(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getPedido(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String action_modifyWitDTO(String codigo, String direccion,
        Date fechaCreacion, Date fechaPedido, Long id, Double total,
        Long id_Usuario) throws Exception {
        try {
            entity.setCodigo(FacesUtils.checkString(codigo));
            entity.setDireccion(FacesUtils.checkString(direccion));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaPedido(FacesUtils.checkDate(fechaPedido));
            entity.setTotal(FacesUtils.checkDouble(total));
            businessDelegatorView.updatePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PedidoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public InputText getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(InputText txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public InputText getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(InputText txtTotal) {
        this.txtTotal = txtTotal;
    }

    public InputText getTxtId_Usuario() {
        return txtId_Usuario;
    }

    public void setTxtId_Usuario(InputText txtId_Usuario) {
        this.txtId_Usuario = txtId_Usuario;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaPedido() {
        return txtFechaPedido;
    }

    public void setTxtFechaPedido(Calendar txtFechaPedido) {
        this.txtFechaPedido = txtFechaPedido;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<PedidoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPedido();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PedidoDTO> pedidoDTO) {
        this.data = pedidoDTO;
    }

    public PedidoDTO getSelectedPedido() {
        return selectedPedido;
    }

    public void setSelectedPedido(PedidoDTO pedido) {
        this.selectedPedido = pedido;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
