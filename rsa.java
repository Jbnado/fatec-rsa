import java.math.BigInteger;
import java.security.SecureRandom;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Rsa_criptografia {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Bem vindo ao trabalho de criptografia RSA", "SISTEMAS DA INFORMAÇÃO", -1);

        String msgcifrada;
        String msgdecifrada;
        BigInteger n, d, e;
        int bitlen = 2048;
        JTextArea component = new JTextArea(10, 20);
        component.setWrapStyleWord(true);
        component.setLineWrap(true);
        component.setCaretPosition(0);
        component.setEditable(false);

        //Escolha de forma aleatória dois números primos grandes p e q
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);

        //Compute n = p * q
        n = p.multiply(q);

        //Compute a função totiente phi(n) = (p -1) (q -1)
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        //Escolha um inteiro  "e"  , 1 < "e" < phi(n) ,  "e" e phi(n) sejam primos entre si.
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        // d seja inverso multiplicativo de "e"
        d = e.modInverse(m);

        String opcaoUsuario = ObterOpcaoUsuario();

        int opcaoConvertida = Integer.parseInt(opcaoUsuario);

        while (opcaoConvertida != 0) {
            switch (opcaoConvertida) {

                case 1:
                    String msg = JOptionPane.showInputDialog("Escreva a mensagem a ser criptografada");

                    msgcifrada = new BigInteger(msg.getBytes()).modPow(e, n).toString();

                    component.setText(msgcifrada);

                    JOptionPane.showMessageDialog(null, new JScrollPane(component), "MENSAGEM CIFRADA", JOptionPane.INFORMATION_MESSAGE);
                    opcaoUsuario = ObterOpcaoUsuario();
                    opcaoConvertida = Integer.parseInt(opcaoUsuario);

                    break;

                case 2:
                    String decript = JOptionPane.showInputDialog("Escreva a mensagem a ser descriptografada");

                    msgdecifrada = new String(new BigInteger(decript).modPow(d, n).toByteArray());
                    JOptionPane.showMessageDialog(null, "Mensagem decifrada: " + msgdecifrada);
                    opcaoUsuario = ObterOpcaoUsuario();
                    opcaoConvertida = Integer.parseInt(opcaoUsuario);

                    break;

                case 3:
                    String variaveis = "p: " + p + "\n\n q: " + q + "\n\n n: " + n + "\n\n e: " + e;
                    component.setText(variaveis);

                    JOptionPane.showMessageDialog(null, new JScrollPane(component), "VARIAVEIS", JOptionPane.INFORMATION_MESSAGE);

                    opcaoUsuario = ObterOpcaoUsuario();
                    opcaoConvertida = Integer.parseInt(opcaoUsuario);

                    break;

                default:
                    JOptionPane.showConfirmDialog(null, "Insira uma opcao valida por favor!", "OPCAO INVALIDA", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                    opcaoUsuario = ObterOpcaoUsuario();
                    opcaoConvertida = Integer.parseInt(opcaoUsuario);

            }

        }

    }

    static String ObterOpcaoUsuario() {
        String opcaoUsuario = JOptionPane.showInputDialog("Escolha a opcao:\n 1 - Criptografar \n 2 - Descriptografar \n 3 - Mostrar variáveis \n 0 - Sair ");
        return opcaoUsuario;
    }
}
