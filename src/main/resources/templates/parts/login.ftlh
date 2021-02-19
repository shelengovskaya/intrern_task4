<#macro login path>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-7">
                <input type="text" name="username" class="form-control" placeholder="User Name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-7">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-success mb-2" type="submit">Log In</button><br>
        <a href="/registration">Sign Up</a>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-success d-flex mb-3" type="submit">Sign Out</button>
    </form>
</#macro>