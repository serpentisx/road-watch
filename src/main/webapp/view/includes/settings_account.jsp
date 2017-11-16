<%@ page contentType="text/html; charset=UTF-8" %>

<div class='main__settings-form username-settings'>
    <form action='/reikningur/breyta-nafni' method='post'>
        <label class='main__input-label'>Þitt notandanafn</label>
        <input name="username" class='main__input' type='text' value="${username}">
        <button class='btn main__save-button' type="submit">Vista</button>
    </form>
</div>
        
<div class='main__settings-form password-settings'>
    <form action='/reikningur/breyta-lykilordi' method='post'>
        <label class='main__input-label'>Gamalt lykilorð</label>
        <input name="old-password" class='main__input' type='password'>
        <label class='main__input-label'>Nýtt lykilorð</label>
        <input name="new-password1" class='main__input' type='password'>
        <label class='main__input-label'>Nýtt lykilorð - endurtekið</label>
        <input name="new-password2" class='main__input' type='password'>
        <button class='btn main__save-button' type="submit">Staðfesta</button>
    </form>
</div>
        
<div class='main__settings-form delete-account'>
    <form action='/reikningur/eyda-reikningi' method='post'>
        <h3>Þú ert að fara að eyða reikningnum þínum. Aðgerðin er óafturkallanleg.</h3>
        <label class='main__input-label'>Lykilorð þitt</label>
        <input name="password" class='main__input' type='password'>
        <button class='btn main__save-button danger-btn' type="submit">Eyða reikningi</button>                      
    </form>
</div>