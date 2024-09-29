package br.com.cesarschool.poo.titulos.test;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
/*
 * Tive problemas pra rodar os testes, essa classe aq aparentemente não ta no repositorio raiz do projeto ai 
 * o vscode n reconhece os testes
 * 
 */
public class RepositorioTituloDividaTest {
    private final String fileName = "TituloDivida.txt";
    private RepositorioTituloDivida repositorio;

    @BeforeEach
    public void setUp() throws IOException {
        // Limpa o arquivo antes de cada teste
        repositorio = new RepositorioTituloDivida();
        Files.deleteIfExists(Paths.get(fileName));
        new File(fileName).createNewFile();
    }

    @Test
    public void testIncluirComSucesso() {
        TituloDivida tituloDivida = new TituloDivida(1, "Brasil", LocalDate.of(2024, 12, 12), 10.5);
        boolean resultado = repositorio.incluir(tituloDivida);
        assertTrue(resultado, "A inclusão deve ser bem-sucedida");
    }

    @Test
    public void testIncluirIdentificadorRepetido() {
        TituloDivida tituloDivida1 = new TituloDivida(1, "Brasil", LocalDate.of(2024, 12, 12), 10.5);
        repositorio.incluir(tituloDivida1);
        
        TituloDivida tituloDivida2 = new TituloDivida(1, "EUA", LocalDate.of(2026, 1, 1), 1.5);
        boolean resultado = repositorio.incluir(tituloDivida2);
        assertFalse(resultado, "Não deve permitir inclusão com identificador repetido");
    }

    @Test
    public void testAlterarComSucesso() {
        TituloDivida tituloDivida = new TituloDivida(1, "Brasil", LocalDate.of(2024, 12, 12), 10.5);
        repositorio.incluir(tituloDivida);

        TituloDivida tituloDividaAlterado = new TituloDivida(1, "Brasil", LocalDate.of(2025, 12, 12), 9.5);
        boolean resultado = repositorio.alterar(tituloDividaAlterado);
        assertTrue(resultado, "A alteração deve ser bem-sucedida");

        TituloDivida tituloBuscado = repositorio.buscar(1);
        assertNotNull(tituloBuscado);
        assertEquals(9.5, tituloBuscado.getTaxaJuros(), "A taxa de juros deve ser atualizada");
    }

    @Test
    public void testAlterarIdentificadorNaoExistente() {
        TituloDivida tituloDivida = new TituloDivida(1, "Brasil", LocalDate.of(2024, 12, 12), 10.5);
        boolean resultado = repositorio.alterar(tituloDivida);
        assertFalse(resultado, "A alteração deve falhar para identificador inexistente");
    }

    @Test
    public void testExcluirComSucesso() {
        TituloDivida tituloDivida = new TituloDivida(1, "Brasil", LocalDate.of(2024, 12, 12), 10.5);
        repositorio.incluir(tituloDivida);

        boolean resultado = repositorio.excluir(1);
        assertTrue(resultado, "A exclusão deve ser bem-sucedida");

        TituloDivida tituloBuscado = repositorio.buscar(1);
        assertNull(tituloBuscado, "O título não deve ser encontrado após a exclusão");
    }

    @Test
    public void testExcluirIdentificadorNaoExistente() {
        boolean resultado = repositorio.excluir(99);
        assertFalse(resultado, "A exclusão deve falhar para identificador inexistente");
    }

    @Test
    public void testBuscarComSucesso() {
        TituloDivida tituloDivida = new TituloDivida(1, "Brasil", LocalDate.of(2024, 12, 12), 10.5);
        repositorio.incluir(tituloDivida);

        TituloDivida tituloBuscado = repositorio.buscar(1);
        assertNotNull(tituloBuscado, "O título deve ser encontrado");
        assertEquals("Brasil", tituloBuscado.getNome());
    }

    @Test
    public void testBuscarIdentificadorNaoExistente() {
        TituloDivida tituloBuscado = repositorio.buscar(99);
        assertNull(tituloBuscado, "O título não deve ser encontrado para identificador inexistente");
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Remove o arquivo após cada teste
        Files.deleteIfExists(Paths.get(fileName));
    }
}
