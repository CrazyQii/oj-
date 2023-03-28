package org.example.oj.dao;

import org.example.oj.entity.Problem;
import org.example.oj.response.ProblemDetailVO;
import org.example.oj.response.ProblemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);

    /**
     * 根据题目Id 随机返回推荐题目
     * @param problemId
     * @param row
     * @return
     */
    List<ProblemDetailVO> listSuggestProblem(@Param("problemId") Integer problemId, @Param("row") int row);

    Integer countRandomProblemId();

    List<ProblemVO> listAll2VO(@Param("flag")Integer flag,@Param("sort") Integer sort, @Param("keyword") String keyword,
                               @Param("level") Integer level, @Param("tagIdsList") List<Integer> tagIdsList);


    ProblemDetailVO getDetailVOById(Integer problemId);

    List<Problem> listAllSolveProblemByUserId(Integer userId);


}