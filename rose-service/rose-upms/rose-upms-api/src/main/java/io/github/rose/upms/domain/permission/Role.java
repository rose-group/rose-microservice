/*
 * Copyright © 2025 rosestack.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rose.upms.domain.permission;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.rose.mybatis.model.TenantEntity;
import javax.validation.constraints.NotBlank;

@TableName("sys_role")
public class Role extends TenantEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "角色名字不能为空")
    private String name;
}
