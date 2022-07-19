package com.deusley.novoProjeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deusley.novoProjeto.domain.Cidade;
import com.deusley.novoProjeto.domain.Cliente;
import com.deusley.novoProjeto.domain.Endereco;
import com.deusley.novoProjeto.domain.enums.TipoCliente;
import com.deusley.novoProjeto.dto.ClienteDTO;
import com.deusley.novoProjeto.dto.ClienteNewDTO;
import com.deusley.novoProjeto.repositories.ClienteRepository;
import com.deusley.novoProjeto.repositories.EnderecoRepository;
import com.deusley.novoProjeto.services.Exceptions.DataIntegrityExcepition;
import com.deusley.novoProjeto.services.Exceptions.ObjectNotFoundExcepition;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository rep;
	
	@Autowired
	private BCryptPasswordEncoder Psw;
	
	@Autowired
	private EnderecoRepository enderecoRepository;


	public Cliente find(Integer id) {
		return rep.findById(id)
				.orElseThrow(() -> new ObjectNotFoundExcepition(" Cliente  de ID: " + id + ", não encontrado!"));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);                                                  
		obj = rep.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj =  find(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
}
	public void delete(Integer id) {
		find(id);
		
		try {
		rep.deleteById(id);
	
		}catch(DataIntegrityViolationException erDelete) {
		throw new DataIntegrityExcepition("Esse cliente não pode ser excluido");
	
}
	}
		public List<Cliente> findAll(){
			return rep.findAll();
		}
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
    	PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    	return rep.findAll(pageRequest);
    }
    public Cliente fromDTO(ClienteDTO objDTO) {
    return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(),null , null, null);
    }
    
    public Cliente fromDTO(ClienteNewDTO objDTO) {
    	
    Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), Psw.encode(objDTO.getSenha()));
    Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
    Endereco end = new Endereco(null, objDTO.getLogradouro(),objDTO.getNumero(),objDTO.getComplemento(),objDTO.getBairro(),objDTO.getCep(), cli, cid);
   
    cli.getEnderecos().add(end)	;
    cli.getTelefones().add(objDTO.getTelefone1());
    if (objDTO.getTelefone2() != null) {
    	cli.getTelefones().add(objDTO.getTelefone2());
    }
    return cli;
    }
    
    private void updateData(Cliente newObj,Cliente obj) {
    	newObj.setNome(obj.getNome());
    	newObj.setEmail(obj.getEmail());
    	
    }
}


//DeusleyDiego//API0030