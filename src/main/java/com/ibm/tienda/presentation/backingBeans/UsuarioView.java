package com.ibm.tienda.presentation.backingBeans;

import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.UsuarioDTO;
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
public class UsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuarioView.class);
    private InputText txtApellido;
    private InputText txtContrasena;
    private InputText txtEmail;
    private InputText txtIdentificacion;
    private InputText txtNombre;
    private InputText txtId_TipoIdentificacion;
    private InputText txtId;
    private Calendar txtFechaNacimiento;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuarioDTO> data;
    private UsuarioDTO selectedUsuario;
    private Usuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsuarioView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedUsuario = null;

        if (txtApellido != null) {
            txtApellido.setValue(null);
            txtApellido.setDisabled(true);
        }

        if (txtContrasena != null) {
            txtContrasena.setValue(null);
            txtContrasena.setDisabled(true);
        }

        if (txtEmail != null) {
            txtEmail.setValue(null);
            txtEmail.setDisabled(true);
        }

        if (txtIdentificacion != null) {
            txtIdentificacion.setValue(null);
            txtIdentificacion.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtId_TipoIdentificacion != null) {
            txtId_TipoIdentificacion.setValue(null);
            txtId_TipoIdentificacion.setDisabled(true);
        }

        if (txtFechaNacimiento != null) {
            txtFechaNacimiento.setValue(null);
            txtFechaNacimiento.setDisabled(true);
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

    public void listener_txtFechaNacimiento() {
        Date inputDate = (Date) txtFechaNacimiento.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = (id != null) ? businessDelegatorView.getUsuario(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellido.setDisabled(false);
            txtContrasena.setDisabled(false);
            txtEmail.setDisabled(false);
            txtIdentificacion.setDisabled(false);
            txtNombre.setDisabled(false);
            txtId_TipoIdentificacion.setDisabled(false);
            txtFechaNacimiento.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtApellido.setValue(entity.getApellido());
            txtApellido.setDisabled(false);
            txtContrasena.setValue(entity.getContrasena());
            txtContrasena.setDisabled(false);
            txtEmail.setValue(entity.getEmail());
            txtEmail.setDisabled(false);
            txtFechaNacimiento.setValue(entity.getFechaNacimiento());
            txtFechaNacimiento.setDisabled(false);
            txtIdentificacion.setValue(entity.getIdentificacion());
            txtIdentificacion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtId_TipoIdentificacion.setValue(entity.getTipoIdentificacion()
                                                    .getId());
            txtId_TipoIdentificacion.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                           .get("selectedUsuario"));
        txtApellido.setValue(selectedUsuario.getApellido());
        txtApellido.setDisabled(false);
        txtContrasena.setValue(selectedUsuario.getContrasena());
        txtContrasena.setDisabled(false);
        txtEmail.setValue(selectedUsuario.getEmail());
        txtEmail.setDisabled(false);
        txtFechaNacimiento.setValue(selectedUsuario.getFechaNacimiento());
        txtFechaNacimiento.setDisabled(false);
        txtIdentificacion.setValue(selectedUsuario.getIdentificacion());
        txtIdentificacion.setDisabled(false);
        txtNombre.setValue(selectedUsuario.getNombre());
        txtNombre.setDisabled(false);
        txtId_TipoIdentificacion.setValue(selectedUsuario.getId_TipoIdentificacion());
        txtId_TipoIdentificacion.setDisabled(false);
        txtId.setValue(selectedUsuario.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuario == null) && (entity == null)) {
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
            entity = new Usuario();

            Long id = FacesUtils.checkLong(txtId);

            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setContrasena(FacesUtils.checkString(txtContrasena));
            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setFechaNacimiento(FacesUtils.checkDate(txtFechaNacimiento));
            entity.setId(id);
            entity.setIdentificacion(FacesUtils.checkString(txtIdentificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTipoIdentificacion((FacesUtils.checkLong(
                    txtId_TipoIdentificacion) != null)
                ? businessDelegatorView.getTipoIdentificacion(
                    FacesUtils.checkLong(txtId_TipoIdentificacion)) : null);
            businessDelegatorView.saveUsuario(entity);
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
                Long id = new Long(selectedUsuario.getId());
                entity = businessDelegatorView.getUsuario(id);
            }

            entity.setApellido(FacesUtils.checkString(txtApellido));
            entity.setContrasena(FacesUtils.checkString(txtContrasena));
            entity.setEmail(FacesUtils.checkString(txtEmail));
            entity.setFechaNacimiento(FacesUtils.checkDate(txtFechaNacimiento));
            entity.setIdentificacion(FacesUtils.checkString(txtIdentificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTipoIdentificacion((FacesUtils.checkLong(
                    txtId_TipoIdentificacion) != null)
                ? businessDelegatorView.getTipoIdentificacion(
                    FacesUtils.checkLong(txtId_TipoIdentificacion)) : null);
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuario = (UsuarioDTO) (evt.getComponent().getAttributes()
                                               .get("selectedUsuario"));

            Long id = new Long(selectedUsuario.getId());
            entity = businessDelegatorView.getUsuario(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getUsuario(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuario(entity);
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

    public String action_modifyWitDTO(String apellido, String contrasena,
        String email, Date fechaNacimiento, Long id, String identificacion,
        String nombre, Long id_TipoIdentificacion) throws Exception {
        try {
            entity.setApellido(FacesUtils.checkString(apellido));
            entity.setContrasena(FacesUtils.checkString(contrasena));
            entity.setEmail(FacesUtils.checkString(email));
            entity.setFechaNacimiento(FacesUtils.checkDate(fechaNacimiento));
            entity.setIdentificacion(FacesUtils.checkString(identificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updateUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(InputText txtApellido) {
        this.txtApellido = txtApellido;
    }

    public InputText getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(InputText txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public InputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail) {
        this.txtEmail = txtEmail;
    }

    public InputText getTxtIdentificacion() {
        return txtIdentificacion;
    }

    public void setTxtIdentificacion(InputText txtIdentificacion) {
        this.txtIdentificacion = txtIdentificacion;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtId_TipoIdentificacion() {
        return txtId_TipoIdentificacion;
    }

    public void setTxtId_TipoIdentificacion(InputText txtId_TipoIdentificacion) {
        this.txtId_TipoIdentificacion = txtId_TipoIdentificacion;
    }

    public Calendar getTxtFechaNacimiento() {
        return txtFechaNacimiento;
    }

    public void setTxtFechaNacimiento(Calendar txtFechaNacimiento) {
        this.txtFechaNacimiento = txtFechaNacimiento;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<UsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuarioDTO> usuarioDTO) {
        this.data = usuarioDTO;
    }

    public UsuarioDTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioDTO usuario) {
        this.selectedUsuario = usuario;
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
