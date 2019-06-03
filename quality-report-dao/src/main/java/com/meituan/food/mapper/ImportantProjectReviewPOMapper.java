package com.meituan.food.mapper;

import com.meituan.food.po.ImportantProjectReviewPO;
import com.meituan.food.po.ImportantProjectReviewPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ImportantProjectReviewPOMapper {
    @SelectProvider(type=ImportantProjectReviewPOSqlProvider.class, method="countByExample")
    long countByExample(ImportantProjectReviewPOExample example);

    @DeleteProvider(type=ImportantProjectReviewPOSqlProvider.class, method="deleteByExample")
    int deleteByExample(ImportantProjectReviewPOExample example);

    @Delete({
        "delete from important_project_review",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into important_project_review (id, content_id, ",
        "km_link, start_date, ",
        "end_date, created_at, ",
        "updated_at)",
        "values (#{id,jdbcType=INTEGER}, #{contentId,jdbcType=BIGINT}, ",
        "#{kmLink,jdbcType=VARCHAR}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR}, #{createdAt,jdbcType=TIMESTAMP}, ",
        "#{updatedAt,jdbcType=TIMESTAMP})"
    })
    int insert(ImportantProjectReviewPO record);

    @InsertProvider(type=ImportantProjectReviewPOSqlProvider.class, method="insertSelective")
    int insertSelective(ImportantProjectReviewPO record);

    @SelectProvider(type=ImportantProjectReviewPOSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content_id", property="contentId", jdbcType=JdbcType.BIGINT),
        @Result(column="km_link", property="kmLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ImportantProjectReviewPO> selectByExample(ImportantProjectReviewPOExample example);

    @Select({
        "select",
        "id, content_id, km_link, start_date, end_date, created_at, updated_at",
        "from important_project_review",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content_id", property="contentId", jdbcType=JdbcType.BIGINT),
        @Result(column="km_link", property="kmLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    ImportantProjectReviewPO selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, content_id, km_link, start_date, end_date, created_at, updated_at",
            "from important_project_review order by created_at desc limit 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="content_id", property="contentId", jdbcType=JdbcType.BIGINT),
            @Result(column="km_link", property="kmLink", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
            @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    ImportantProjectReviewPO selectLastData();

    @UpdateProvider(type=ImportantProjectReviewPOSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ImportantProjectReviewPO record, @Param("example") ImportantProjectReviewPOExample example);

    @UpdateProvider(type=ImportantProjectReviewPOSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ImportantProjectReviewPO record, @Param("example") ImportantProjectReviewPOExample example);

    @UpdateProvider(type=ImportantProjectReviewPOSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ImportantProjectReviewPO record);

    @Update({
        "update important_project_review",
        "set content_id = #{contentId,jdbcType=BIGINT},",
          "km_link = #{kmLink,jdbcType=VARCHAR},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR},",
          "created_at = #{createdAt,jdbcType=TIMESTAMP},",
          "updated_at = #{updatedAt,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ImportantProjectReviewPO record);
}