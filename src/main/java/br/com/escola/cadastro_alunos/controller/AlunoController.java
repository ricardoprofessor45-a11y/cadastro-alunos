package br.com.escola.cadastro_alunos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.escola.cadastro_alunos.model.Aluno;
import br.com.escola.cadastro_alunos.repository.AlunoRepository;

@Controller
public class AlunoController {
	private final AlunoRepository alunoRepository;

	public AlunoController(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@GetMapping("/")
	public String paginaInicial() {
		return "redirect:/alunos";
	}

	@GetMapping("/alunos")
	public String listarAlunos(Model model) {
		model.addAttribute("aluno", new Aluno());
		model.addAttribute("alunos", alunoRepository.listarTodos());
		return "alunos";
	}

	@PostMapping("/alunos")
	public String salvarAluno(@ModelAttribute Aluno aluno, RedirectAttributes redirectAttributes) {
		alunoRepository.salvar(aluno);
		redirectAttributes.addFlashAttribute("mensagem", "Aluno cadastrado com sucesso!");
		return "redirect:/alunos";
	}

	@GetMapping("/alunos/{id}/editar")
	public String abrirFormularioEdicao(@PathVariable Integer id, Model model) {
		Aluno aluno = alunoRepository.buscarPorId(id);
		model.addAttribute("aluno", aluno);
		return "editar-aluno";
	}

	@PostMapping("/alunos/atualizar")
	public String atualizarAluno(@ModelAttribute Aluno aluno, RedirectAttributes redirectAttributes) {
		alunoRepository.atualizar(aluno);
		redirectAttributes.addFlashAttribute("mensagem", "Aluno atualizado com sucesso!");
		return "redirect:/alunos";
	}

	@PostMapping("/alunos/{id}/excluir")
	public String excluirAluno(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

		alunoRepository.excluir(id);
		redirectAttributes.addFlashAttribute("mensagem", "Aluno excluído com sucesso!");
		return "redirect:/alunos";
	}
}