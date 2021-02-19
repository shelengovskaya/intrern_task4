<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#macro if if then else=""><#if if>${then}<#else>${else}</#if></#macro>
<@c.page>
    <@l.logout />
    <form action="/user/delete" method="post">
        <div class="d-flex mb-3">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group btn-group-lg" role="group" aria-label="First group">
                    <button type="submit" class="btn btn-success" name="block">Block</button>
                    <button type="submit" class="btn btn-success" name="unblock">Unblock</button>
                    <button type="submit" class="btn btn-success" name="delete">Delete</button>
                </div>
            </div>
        </div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    <table class="table table-black table-bordered">
        <thead class="table-success">
        <tr>
            <th>
                <input type="checkbox" name="checkAll">
            </th>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Registration Date</th>
            <th>Last Login</th>
            <th>Status</th>
        </tr>
        </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>
                <input type="checkbox" name="idChecked" about="def" value="${user.username}">
            </td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email!}</td>
            <td>${user.regDate?string('dd-MM-yyyy HH:mm:ss')!}</td>
            <td>${user.lastLogin?datetime?string('dd-MM-yyyy HH:mm:ss')!""}</td>
            <td><#list user.roles as role>
                    <@if (role=="ADMIN") "Unblock" "Block" />
                </#list>
            </td>
        </tr>
    </#list>
    </tbody>
    </table>

    </form>

    <script>
        const others = document.querySelectorAll("input[about=def]");
        document.querySelector("input[name=checkAll]").addEventListener('change', function () {
            for (var i = 0; i < others.length; i++) others[i].checked = this.checked;
        });
    </script>

</@c.page>