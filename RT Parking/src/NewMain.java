
import util.JNumberFormatField;
import controller.FuncionariosJpaController;
import controller.exceptions.NonexistentEntityException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.NumberFormatter;
import model.entidade.Funcionarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ronan
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException, UnsupportedEncodingException, NonexistentEntityException, Exception {
        /* DateFormat dateEU = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateBR = new SimpleDateFormat("dd/MM/yyyy");

        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("RT_ParkingPU");
        EntityManager manager = objFactory.createEntityManager();

        Funcionarios f = new Funcionarios();
        FuncionariosJpaController fJpa = new FuncionariosJpaController(objFactory);

        f = fJpa.findFuncionarios(1);

        f.setIdfuncionario(1);
        f.setNome("Ronan Adriel Zenatti");
        f.setCpf("355.936.478-79");
        f.setRg("41.324.990-6");
        f.setDtNasc(dateBR.parse("25/02/1988"));
        f.setRua("Rua Floriano Peixoto");
        f.setNumero("13-41");
        f.setBairro("Jardim Estoril");
        f.setCidade("Bauru-SP");
        f.setTelefones("(14) 9 8157-5657 / 9 9800-4511");
        f.setEmail("ronanzenatti@gmail.com");
        f.setSalario(BigDecimal.valueOf(900));
        f.setDtInicio(dateBR.parse("01/01/2017"));
        f.setUsuario("ronan");
        String base64encodedString = Base64.getEncoder().encodeToString("n2gm2772".getBytes("utf-8"));
        f.setSenha(base64encodedString);
        f.setTipo('A');
        fJpa.edit(f);
        List<Funcionarios> list = fJpa.findFuncionariosEntities();

        for (Funcionarios fl : list) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("Id: " + fl.getIdfuncionario() + " - Nome: " + fl.getNome());
            System.out.println("Telefones: " + fl.getTelefones());
            System.out.println("-----------------------------------------------------------");
        }

        System.out.println(base64encodedString);

        byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
        System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));*/

        JFrame frame = new JFrame("Teste do campo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new JNumberFormatField(new DecimalFormat("R$ 0.00")) {
            {// limita a 6 
                // caracteres  
                setLimit(12);
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
