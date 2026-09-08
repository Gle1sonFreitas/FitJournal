package com.fitjournal.api.repository;

import com.fitjournal.api.dto.AddRotinaDTO;
import com.fitjournal.api.dto.ExercicioDTO;
import com.fitjournal.api.dto.GetRotinaDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TreinoRepository {

    private JdbcTemplate jdbcTemplate;

    public TreinoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GetRotinaDTO> listarRotinas(int id){
        String sql = """
                SELECT
                    r.nome AS nomeRotina,
                    COUNT(DISTINCT e.id) AS qtdExercicios,
                    MAX(t.data_hora) AS ultimoTreino
                FROM rotinas r
                INNER JOIN usuarios u ON r.id_usuario = u.id
                LEFT JOIN rotina_exercicios re ON r.id = re.id_rotina
                LEFT JOIN exercicios e ON re.id_exercicio = e.id
                LEFT JOIN treinos t ON t.id_rotina = r.id
                WHERE u.id = ?
                GROUP BY r.id, r.nome;
                """;
        try {
            List<GetRotinaDTO> rotinas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetRotinaDTO.class), id);
            return rotinas;
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public void addRotina(AddRotinaDTO rotina){
        System.out.println(rotina.toString());

        String sqlRotina = "INSERT INTO rotinas (id_usuario, nome) VALUES (?, ?) ";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(
                        sqlRotina,
                        Statement.RETURN_GENERATED_KEYS
                );

                statement.setInt(1, rotina.getIdUsuario());
                statement.setString(2, rotina.getNome());
                return statement;
            }, keyHolder);

            Integer id = keyHolder.getKeyAs(Integer.class);

            rotina.setId(id);

            addExercicios(rotina.getExercicios(), rotina.getId());
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public void addExercicios(List<ExercicioDTO> exercicios, Integer idRotina){
        String sqlExercicio = "INSERT INTO exercicios (nome, grupo_muscular, equipamento) VALUES (?, ?, ?) ";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            for (int i = 0; exercicios.size() > i ; i++) {
                Integer index = i;
                jdbcTemplate.update(con -> {
                    PreparedStatement statement = con.prepareStatement(
                            sqlExercicio,
                            Statement.RETURN_GENERATED_KEYS
                    );

                    statement.setString(1, exercicios.get(index).getNome());
                    statement.setString(2, exercicios.get(index).getGrupo());
                    statement.setString(3, exercicios.get(index).getEquipamento());
                    return statement;
                }, keyHolder);

                Integer id = keyHolder.getKeyAs(Integer.class);

                exercicios.get(i).setId(id);

                if (idRotina != null){
                    addRotinaExercicio(exercicios.get(i), idRotina);
                }
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public void addRotinaExercicio(ExercicioDTO rotinaExercicio, Integer idRotina){
        String sql = "INSERT INTO rotina_exercicios (id_rotina, id_exercicio) VALUES(?, ?)";
        try {
            jdbcTemplate.update(sql, idRotina, rotinaExercicio.getId());
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

}
