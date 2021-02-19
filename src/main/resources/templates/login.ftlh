<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-4">
        <h2>Login page</h2>
    </div>
    <@l.login "/login" />
</@c.page>