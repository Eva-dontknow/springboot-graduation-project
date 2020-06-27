package com.example.springbootgraduationproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

//防止spring容器通过动态代理创建该接口的对象，
// 因为我们的慕斯不是创建该接口的对象，而是创建每一个实体对应的repository对象
@NoRepositoryBean
/*因为原生的spring-date-jpa没有提供refresh方法
所以现在沃尔玛需要创建一个新的接口来暴露那些接口
 */
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
     T refresh(T t);//暴露refresh的接口,强制把数据库中的信息同步到缓存
}
