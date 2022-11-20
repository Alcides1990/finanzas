package finanzas.finanzas.repository;

import java.util.List;
import java.util.Map;

import finanzas.finanzas.models.PersonaModel;

public interface IPersonaRepository {
    List<PersonaModel> findAll();
    PersonaModel findByCodigo(String codigo);
    String save(PersonaModel persona);
    String update(int id, PersonaModel persona);
    String delete(int id);
}
