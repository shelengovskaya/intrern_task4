<#import "parts/common.ftlh" as c>
<#import "parts/registration.ftlh" as l>
<@c.page>
    <div class="mb-4">
        <h2>Add new user</h2>
    </div>
    ${message!}
    <@l.registration "/registration" />
</@c.page>