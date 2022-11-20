package finanzas.finanzas.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import finanzas.finanzas.models.PersonaModel;

@Repository
public class ImpIPersonaRepository implements IPersonaRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private NamedParameterJdbcTemplate jdbcParameters;


    private String sql="";
    private String retorno="";

    @Override
    public List<PersonaModel> findAll() {
       sql="SELECT * FROM public.persona";
       List<PersonaModel> datos=jdbc.query(sql,((rs, rowNum) -> new PersonaModel(rs.getInt("id"),
                                                                                 rs.getString("cod_persona"),
                                                                                 rs.getString("per_nombre"),
                                                                                 rs.getDate("per_registro"),
                                                                                 rs.getBoolean("per_estado"))
       ));
        return datos;
    }

    @Override
    public PersonaModel findByCodigo(String codigo) {
        sql="SELECT * FROM public.persona where cod_persona = :codigo";
        List<PersonaModel> datos = jdbcParameters.query(sql,
        new MapSqlParameterSource("codigo", codigo), (rs, i) -> {
            return new PersonaModel(rs.getInt("id"),
                                    rs.getString("cod_persona"),
                                    rs.getString("per_nombre"), 
                                    rs.getDate("per_registro"),
                                    rs.getBoolean("per_estado"));
        });
        return datos.get(0);
    }

    @Override
    public String save(PersonaModel persona) {
        sql="SELECT public.sp_persona(1,0, :nombre, :codigo)";
        try{
        retorno= jdbcParameters.query(sql, 
                                        new MapSqlParameterSource("nombre",persona.getPer_nombre())
                                        .addValue("codigo", persona.getCod_persona()),(rs) -> {
                                            rs.next();
                                            return rs.getString(1);
                                        });
        return retorno;
        } catch(Exception ex){
            return retorno=ex.getMessage();
        }
    }

    @Override
    public String update(int id, PersonaModel persona) {
        sql="SELECT public.sp_persona(2, :id_persona, :nombre, :codigo) ";
        retorno = jdbcParameters.query(sql, 
                                            new MapSqlParameterSource("nombre",persona.getPer_nombre())
                                            .addValue("codigo", persona.getCod_persona())
                                            .addValue("id_persona",id),(rs)->{
                                                rs.next();
                                                return rs.getString(1);
                                            });
        return retorno; 
    }

    @Override
    public String delete(int id) {
        sql="SELECT public.sp_persona(3, :id_persona, '', '') ";
        retorno = jdbcParameters.query(sql, 
                                            new MapSqlParameterSource("id_persona",id),(rs)->{
                                                rs.next();
                                                return rs.getString(1);
                                            });
        return retorno; 
        
    }

    
}
