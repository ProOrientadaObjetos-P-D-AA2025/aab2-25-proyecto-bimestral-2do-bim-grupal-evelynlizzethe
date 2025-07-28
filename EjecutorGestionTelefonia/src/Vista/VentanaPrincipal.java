/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ClienteControlador;
import Controlador.FacturaControlador;
import Controlador.PlanControlador;
import Modelo.Cliente;
import Modelo.Dispositivo;
import Modelo.Factura;
import Modelo.PlanMovil;
import Modelo.PlanPostPagoMinutos;
import Modelo.PlanPostPagoMinutosMegas;
import Modelo.PlanPostPagoMinutosMegasEconomico;
import Modelo.PlanPostPagosMegas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private int planSeleccionadoId = -1;
    private int clienteSeleccionadoId = -1;
    private PlanControlador planControlador = new PlanControlador();
    private ClienteControlador clienteControlador = new ClienteControlador();
    private FacturaControlador facturaControlador = new FacturaControlador();

    public VentanaPrincipal() {
        initComponents();
        limpiarFormulario();
        listarClientes();
        cmbTipoPlan.addActionListener(evt -> onTipoPlanChanged());
        btnCrearFactura.addActionListener(e -> crearFactura());
        btnListarFacturas.addActionListener(e -> listarFacturas());
        btnEliminarFactura.addActionListener(e -> eliminarFactura());
    }

    private void listarClientes() {
        DefaultTableModel m = new DefaultTableModel(
                new String[]{"ID", "Nombres", "Cédula", "Ciudad", "Correo", "Carrera", "Marca", "Modelo", "Número"}, 0);
        for (Cliente c : clienteControlador.obtenerClientes()) {
            String marca = c.getDispositivo() != null ? c.getDispositivo().getMarca() : "";
            String modelo = c.getDispositivo() != null ? c.getDispositivo().getModelo() : "";
            String numero = c.getDispositivo() != null ? c.getDispositivo().getNumero() : "";
            m.addRow(new Object[]{
                c.getIdCliente(),
                c.getNombres(),
                c.getCedulaPasaporte(),
                c.getCiudad(),
                c.getCorreo(),
                c.getCarrera(),
                marca,
                modelo,
                numero
            });
        }
        tblClientes.setModel(m);
    }

    private void limpiarFormulario() {
        txtNombres.setText("");
        txtCedula.setText("");
        txtCiudad.setText("");
        txtCorreo.setText("");
        txtCarrera.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtNumero.setText("");
        clienteSeleccionadoId = -1;
    }

    private void listarPlanes() {
        String[] cols = {"ID Plan", "ID Cliente", "Tipo Plan", "Pago Mensual"};
        DefaultTableModel m = new DefaultTableModel(cols, 0);
        for (PlanMovil p : planControlador.obtenerPlanes()) {
            m.addRow(new Object[]{
                p.getIdPlan(),
                p.getIdCliente(),
                p.getTipoPlan(),
                String.format("%.2f", p.calcularPagoMensual())
            });
        }
        tblPlanes.setModel(m);
        planSeleccionadoId = -1;
    }

    private void limpiarFormularioPlanes() {
        txtIdCliente.setText("");
        cmbTipoPlan.setSelectedIndex(0);
        txtP1.setText("");
        txtP2.setText("");
        txtP3.setText("");
        txtP4.setText("");
        txtP5.setText("");
    }

    private void onTipoPlanChanged() {
        cmbTipoPlan.addActionListener(e -> {
            String tipo = (String) cmbTipoPlan.getSelectedItem();
            lblP1.setText("");
            txtP1.setVisible(false);
            lblP1.setVisible(false);
            lblP2.setText("");
            txtP2.setVisible(false);
            lblP2.setVisible(false);
            lblP3.setText("");
            txtP3.setVisible(false);
            lblP3.setVisible(false);
            lblP4.setText("");
            txtP4.setVisible(false);
            lblP4.setVisible(false);
            lblP5.setText("");
            txtP5.setVisible(false);
            lblP5.setVisible(false);

            switch (tipo) {
                case "PostPagoMinutos":
                    lblP1.setText("Min Nac.");
                    txtP1.setVisible(true);
                    lblP1.setVisible(true);
                    lblP2.setText("Costo Min Nac.");
                    txtP2.setVisible(true);
                    lblP2.setVisible(true);
                    lblP3.setText("Min Int.");
                    txtP3.setVisible(true);
                    lblP3.setVisible(true);
                    lblP4.setText("Costo Min Int.");
                    txtP4.setVisible(true);
                    lblP4.setVisible(true);
                    break;
                case "PostPagoMinutosMegas":
                    lblP1.setText("Minutos");
                    txtP1.setVisible(true);
                    lblP1.setVisible(true);
                    lblP2.setText("Costo Min");
                    txtP2.setVisible(true);
                    lblP2.setVisible(true);
                    lblP3.setText("Gigas");
                    txtP3.setVisible(true);
                    lblP3.setVisible(true);
                    lblP4.setText("Costo Giga");
                    txtP4.setVisible(true);
                    lblP4.setVisible(true);
                    break;
                case "PostPagoMinutosMegasEconomico":
                    lblP1.setText("Minutos");
                    txtP1.setVisible(true);
                    lblP1.setVisible(true);
                    lblP2.setText("Costo Min");
                    txtP2.setVisible(true);
                    lblP2.setVisible(true);
                    lblP3.setText("Gigas");
                    txtP3.setVisible(true);
                    lblP3.setVisible(true);
                    lblP4.setText("Costo Giga");
                    txtP4.setVisible(true);
                    lblP4.setVisible(true);
                    lblP5.setText("Descuento %");
                    txtP5.setVisible(true);
                    lblP5.setVisible(true);
                    break;
                case "PostPagoMegas":
                    lblP1.setText("Gigas");
                    txtP1.setVisible(true);
                    lblP1.setVisible(true);
                    lblP2.setText("Costo Giga");
                    txtP2.setVisible(true);
                    lblP2.setVisible(true);
                    lblP3.setText("Tarifa base");
                    txtP3.setVisible(true);
                    lblP3.setVisible(true);
                    break;
            }
        });

    }

    private void listarFacturas() {
        DefaultTableModel m = new DefaultTableModel(
                new String[]{"ID Fact", "ID Cliente", "ID Plan", "Total", "Fecha"}, 0
        );
        for (Factura f : facturaControlador.obtenerFacturas()) {
            m.addRow(new Object[]{
                f.getIdFactura(),
                f.getIdCliente(),
                f.getIdPlan(),
                String.format("%.2f", f.getTotalPagar()),
                f.getFechaEmision()
            });
        }
        tblFacturas.setModel(m);
    }

    private void crearFactura() {
        try {
            int idC = Integer.parseInt(txtIdClienteFactura.getText().trim());
            int idP = Integer.parseInt(txtIdPlanFactura.getText().trim());
            PlanMovil plan = planControlador.obtenerPlanes()
                    .stream()
                    .filter(p -> p.getIdPlan() == idP)
                    .findFirst().orElse(null);
            if (plan == null) {
                JOptionPane.showMessageDialog(this, "Plan no encontrado");
                return;
            }
            Factura f = new Factura(
                    idC,
                    idP,
                    plan.calcularPagoMensual(),
                    java.time.LocalDate.now().toString()
            );
            if (facturaControlador.crearFactura(f)) {
                JOptionPane.showMessageDialog(this, "Factura creada con ID " + f.getIdFactura());
                listarFacturas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear factura");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "IDs deben ser numéricos");
        }
    }

    private void eliminarFactura() {
        try {
            int idF = Integer.parseInt(txtIdFacturaEliminar.getText().trim());
            if (facturaControlador.eliminarFactura(idF)) {
                JOptionPane.showMessageDialog(this, "Factura eliminada");
                listarFacturas();
            } else {
                JOptionPane.showMessageDialog(this, "No existe esa factura");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID factura inválido");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtCarrera = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        btnEliminarCliente = new javax.swing.JButton();
        btnListarCliente = new javax.swing.JButton();
        btnActualizarCliente = new javax.swing.JButton();
        btnCrearCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbTipoPlan = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtP1 = new javax.swing.JTextField();
        txtP2 = new javax.swing.JTextField();
        txtP3 = new javax.swing.JTextField();
        txtP4 = new javax.swing.JTextField();
        txtP5 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlanes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblP1 = new javax.swing.JLabel();
        lblP3 = new javax.swing.JLabel();
        lblP2 = new javax.swing.JLabel();
        lblP4 = new javax.swing.JLabel();
        lblP5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIdPlanEliminar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIdClienteFactura = new javax.swing.JTextField();
        txtIdPlanFactura = new javax.swing.JTextField();
        txtIdFacturaEliminar = new javax.swing.JTextField();
        btnCrearFactura = new javax.swing.JButton();
        btnListarFacturas = new javax.swing.JButton();
        btnEliminarFactura = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblFacturas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombres");

        jLabel2.setText("Cedula");

        jLabel3.setText("Ciudad");

        jLabel4.setText("Correo");

        jLabel5.setText("Carrera");

        txtCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarreraActionPerformed(evt);
            }
        });

        jLabel6.setText("Marca");

        jLabel7.setText("Modelo");

        jLabel8.setText("Numero");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnListarCliente.setText("Listar");
        btnListarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarClienteActionPerformed(evt);
            }
        });

        btnActualizarCliente.setText("Actualizar");
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });

        btnCrearCliente.setText("Crear");
        btnCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearClienteActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(txtCedula)
                            .addComponent(txtCiudad)
                            .addComponent(txtCorreo)
                            .addComponent(txtCarrera)
                            .addComponent(txtMarca)
                            .addComponent(txtModelo)
                            .addComponent(txtNumero))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnListarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCrearCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnCrearCliente)
                        .addGap(31, 31, 31)
                        .addComponent(btnListarCliente)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnActualizarCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Clientes", jPanel1);

        jLabel9.setText("ID Cliente");

        jLabel10.setText("Tipo Plan");

        cmbTipoPlan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PostPagoMinutos", "PostPagoMinutosMegas", "PostPagoMinutosMegasEconomico", "PostPagoMegas" }));

        jLabel11.setText("Parametros");

        txtP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtP4ActionPerformed(evt);
            }
        });

        tblPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_plan", "id_cliente", "tipo_plan", "pago_mensual"
            }
        ));
        tblPlanes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlanesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPlanes);

        jButton1.setText("Crear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Listar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblP1.setText("jLabel12");

        lblP3.setText("jLabel13");

        lblP2.setText("jLabel14");

        lblP4.setText("jLabel15");

        lblP5.setText("jLabel16");

        jLabel12.setText("ID Plan a borrar:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(jLabel11))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                                    .addComponent(lblP2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtIdCliente)
                                        .addComponent(cmbTipoPlan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtP2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblP1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(lblP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblP4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblP5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtP1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtP3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtP5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIdPlanEliminar))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtP4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel12)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(cmbTipoPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(lblP1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblP2))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(lblP3))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblP4)
                    .addComponent(txtP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(txtIdPlanEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblP5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Plan Movil", jPanel3);

        jLabel13.setText("Id Cliente");

        jLabel14.setText("Id Plan");

        jLabel16.setText("Id Factura");

        txtIdFacturaEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdFacturaEliminarActionPerformed(evt);
            }
        });

        btnCrearFactura.setText("Generar Factura");
        btnCrearFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFacturaActionPerformed(evt);
            }
        });

        btnListarFacturas.setText("Listar Facturas");
        btnListarFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarFacturasActionPerformed(evt);
            }
        });

        btnEliminarFactura.setText("Eliminar Factura");
        btnEliminarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFacturaActionPerformed(evt);
            }
        });

        tblFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblFacturas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdFacturaEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdPlanFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdClienteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCrearFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnListarFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 99, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtIdClienteFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearFactura))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtIdPlanFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListarFacturas))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdFacturaEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(btnEliminarFactura))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Facturas", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarreraActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void btnCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearClienteActionPerformed
        Cliente c = new Cliente(
                txtNombres.getText().trim(),
                txtCedula.getText().trim(),
                txtCiudad.getText().trim(),
                txtCorreo.getText().trim(),
                txtCarrera.getText().trim(),
                new Dispositivo(txtMarca.getText().trim(),
                        txtModelo.getText().trim(),
                        txtNumero.getText().trim())
        );
        if (clienteControlador.crearCliente(c)) {
            JOptionPane.showMessageDialog(this, "Cliente creado con éxito");
            limpiarFormulario();
            listarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear cliente");
        }
    }//GEN-LAST:event_btnCrearClienteActionPerformed

    private void btnListarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarClienteActionPerformed
        listarClientes();
    }//GEN-LAST:event_btnListarClienteActionPerformed

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
        if (clienteSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione primero un cliente");
            return;
        }
        Cliente c = clienteControlador.buscarPorId(clienteSeleccionadoId);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado");
            return;
        }

        c.setNombres(txtNombres.getText());
        c.setCedulaPasaporte(txtCedula.getText());
        c.setCiudad(txtCiudad.getText());
        c.setCorreo(txtCorreo.getText());
        c.setCarrera(txtCarrera.getText());

        if (c.getDispositivo() == null) {
            c.setDispositivo(new Dispositivo(
                    txtMarca.getText(),
                    txtModelo.getText(),
                    txtNumero.getText()
            ));
        } else {
            c.getDispositivo().setMarca(txtMarca.getText());
            c.getDispositivo().setModelo(txtModelo.getText());
            c.getDispositivo().setNumero(txtNumero.getText());
        }

        if (clienteControlador.actualizarCliente(c)) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado");
            limpiarFormulario();
            listarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar");
        }
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        if (clienteSeleccionadoId < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione primero un cliente");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar al cliente con ID " + clienteSeleccionadoId + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );
        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        if (clienteControlador.eliminarCliente(clienteSeleccionadoId)) {
            JOptionPane.showMessageDialog(this, "Cliente eliminado");
            limpiarFormulario();
            listarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar");
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        int row = tblClientes.getSelectedRow();
        if (row < 0) {
            return;
        }
        int id = (int) tblClientes.getValueAt(row, 0);
        txtNombres.setText((String) tblClientes.getValueAt(row, 1));
        txtCedula.setText((String) tblClientes.getValueAt(row, 2));
        txtCiudad.setText((String) tblClientes.getValueAt(row, 3));
        txtCorreo.setText((String) tblClientes.getValueAt(row, 4));
        txtCarrera.setText((String) tblClientes.getValueAt(row, 5));
        txtMarca.setText((String) tblClientes.getValueAt(row, 6));
        txtModelo.setText((String) tblClientes.getValueAt(row, 7));
        txtNumero.setText((String) tblClientes.getValueAt(row, 8));
        this.clienteSeleccionadoId = id;
    }//GEN-LAST:event_tblClientesMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        listarPlanes();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int idC = Integer.parseInt(txtIdCliente.getText().trim());
            String tipo = (String) cmbTipoPlan.getSelectedItem();
            PlanMovil p = null;
            switch (tipo) {
                case "PostPagoMinutos":
                    p = new PlanPostPagoMinutos(
                            idC,
                            Integer.parseInt(txtP1.getText()),
                            Double.parseDouble(txtP2.getText()),
                            Integer.parseInt(txtP3.getText()),
                            Double.parseDouble(txtP4.getText())
                    );
                    break;
                case "PostPagoMinutosMegas":
                    p = new PlanPostPagoMinutosMegas(
                            idC,
                            Integer.parseInt(txtP1.getText()),
                            Double.parseDouble(txtP2.getText()),
                            Double.parseDouble(txtP3.getText()),
                            Double.parseDouble(txtP4.getText())
                    );
                    break;
                case "PostPagoMinutosMegasEconomico":
                    p = new PlanPostPagoMinutosMegasEconomico(
                            idC,
                            Integer.parseInt(txtP1.getText()),
                            Double.parseDouble(txtP2.getText()),
                            Double.parseDouble(txtP3.getText()),
                            Double.parseDouble(txtP4.getText()),
                            Double.parseDouble(txtP5.getText())
                    );
                    break;
                case "PostPagoMegas":
                    p = new PlanPostPagosMegas(
                            idC,
                            Double.parseDouble(txtP1.getText()),
                            Double.parseDouble(txtP2.getText()),
                            Double.parseDouble(txtP3.getText())
                    );
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Tipo de plan inválido");
                    return;
            }
            if (planControlador.crearPlan(p)) {
                JOptionPane.showMessageDialog(this, "Plan creado con éxito");
                limpiarFormularioPlanes();
                listarPlanes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear plan");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revisa que todos los parámetros sean numéricos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String idText = txtIdPlanEliminar.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID del plan a eliminar");
            return;
        }
        int idPlan;
        try {
            idPlan = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero");
            return;
        }

        int opc = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quiere eliminar el plan con ID " + idPlan + "?",
                "Confirmar borrado",
                JOptionPane.YES_NO_OPTION
        );
        if (opc != JOptionPane.YES_OPTION) {
            return;
        }

        if (planControlador.eliminarPlan(idPlan)) {
            JOptionPane.showMessageDialog(this, "Plan eliminado");
            txtIdPlanEliminar.setText("");
            listarPlanes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar plan o plan no existe");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblPlanesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlanesMouseClicked
    }//GEN-LAST:event_tblPlanesMouseClicked

    private void txtP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtP4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtP4ActionPerformed

    private void txtIdFacturaEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdFacturaEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdFacturaEliminarActionPerformed

    private void btnCrearFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFacturaActionPerformed
    }//GEN-LAST:event_btnCrearFacturaActionPerformed

    private void btnListarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarFacturasActionPerformed
    }//GEN-LAST:event_btnListarFacturasActionPerformed

    private void btnEliminarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFacturaActionPerformed
    }//GEN-LAST:event_btnEliminarFacturaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnCrearCliente;
    private javax.swing.JButton btnCrearFactura;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarFactura;
    private javax.swing.JButton btnListarCliente;
    private javax.swing.JButton btnListarFacturas;
    private javax.swing.JComboBox<String> cmbTipoPlan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    private javax.swing.JLabel lblP3;
    private javax.swing.JLabel lblP4;
    private javax.swing.JLabel lblP5;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblFacturas;
    private javax.swing.JTable tblPlanes;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdClienteFactura;
    private javax.swing.JTextField txtIdFacturaEliminar;
    private javax.swing.JTextField txtIdPlanEliminar;
    private javax.swing.JTextField txtIdPlanFactura;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtP1;
    private javax.swing.JTextField txtP2;
    private javax.swing.JTextField txtP3;
    private javax.swing.JTextField txtP4;
    private javax.swing.JTextField txtP5;
    // End of variables declaration//GEN-END:variables
}
