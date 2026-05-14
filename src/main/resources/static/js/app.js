const API = '/api';

const $ = (sel) => document.querySelector(sel);
const formatMoeda = (v) => 'R$ ' + Number(v).toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
const formatKm = (v) => Number(v).toLocaleString('pt-BR') + ' km';

function toast(msg, tipo = 'sucesso') {
    const el = $('#toast');
    el.textContent = msg;
    el.className = 'toast ' + tipo;
    setTimeout(() => el.classList.add('hidden'), 3000);
}

async function api(path, opts = {}) {
    const r = await fetch(API + path, {
        headers: { 'Content-Type': 'application/json' },
        ...opts
    });
    if (!r.ok) {
        let msg = 'Erro ' + r.status;
        try {
            const data = await r.json();
            msg = data.mensagem || msg;
        } catch (_) {}
        throw new Error(msg);
    }
    return r.status === 204 ? null : r.json();
}

// ---------- TABS DO CADASTRO ----------
document.querySelectorAll('.tab').forEach(tab => {
    tab.addEventListener('click', () => {
        document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
        tab.classList.add('active');
        document.querySelectorAll('#form-veiculo, #form-modelo, #form-marca')
            .forEach(f => f.classList.add('hidden'));
        $('#form-' + tab.dataset.tab).classList.remove('hidden');
    });
});

// ---------- MARCAS ----------
async function carregarMarcas() {
    const marcas = await api('/marcas');
    const seletores = ['#filtro-marca', '#filtro-marca-form', '#modelo-marca'];
    seletores.forEach(sel => {
        const select = $(sel);
        const valorAtual = select.value;
        const isFiltro = sel === '#filtro-marca';
        select.innerHTML = isFiltro ? '<option value="">Todas</option>' : '<option value="">Selecione</option>';
        marcas.forEach(m => {
            const op = document.createElement('option');
            op.value = m.id;
            op.textContent = m.nome;
            select.appendChild(op);
        });
        select.value = valorAtual;
    });
}

$('#form-marca').addEventListener('submit', async (e) => {
    e.preventDefault();
    try {
        await api('/marcas', {
            method: 'POST',
            body: JSON.stringify({ nome: $('#marca-nome').value, paisOrigem: $('#marca-pais').value })
        });
        toast('Marca cadastrada');
        $('#form-marca').reset();
        carregarMarcas();
    } catch (err) { toast(err.message, 'erro'); }
});

// ---------- MODELOS ----------
async function carregarModelos(marcaId, alvoSeletor) {
    const url = marcaId ? `/modelos?marcaId=${marcaId}` : '/modelos';
    const modelos = await api(url);
    const select = $(alvoSeletor);
    const valorAtual = select.value;
    const isFiltro = alvoSeletor === '#filtro-modelo';
    select.innerHTML = isFiltro ? '<option value="">Todos</option>' : '<option value="">Selecione</option>';
    modelos.forEach(m => {
        const op = document.createElement('option');
        op.value = m.id;
        op.textContent = m.nome + (m.marca ? ' (' + m.marca.nome + ')' : '');
        select.appendChild(op);
    });
    select.value = valorAtual;
}

$('#filtro-marca').addEventListener('change', (e) => carregarModelos(e.target.value, '#filtro-modelo'));
$('#filtro-marca-form').addEventListener('change', (e) => carregarModelos(e.target.value, '#modelo-form'));

$('#form-modelo').addEventListener('submit', async (e) => {
    e.preventDefault();
    try {
        await api('/modelos', {
            method: 'POST',
            body: JSON.stringify({
                nome: $('#modelo-nome').value,
                categoria: $('#modelo-categoria').value || null,
                marcaId: Number($('#modelo-marca').value)
            })
        });
        toast('Modelo cadastrado');
        $('#form-modelo').reset();
        carregarModelos(null, '#modelo-form');
    } catch (err) { toast(err.message, 'erro'); }
});

// ---------- VEICULOS ----------
$('#form-veiculo').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = $('#veiculo-id').value;
    const payload = {
        modeloId: Number($('#modelo-form').value),
        ano: Number($('#ano').value),
        cor: $('#cor').value,
        preco: Number($('#preco').value),
        quilometragem: Number($('#quilometragem').value),
        status: $('#status-form').value
    };
    try {
        if (id) {
            await api('/veiculos/' + id, { method: 'PUT', body: JSON.stringify(payload) });
            toast('Veiculo atualizado');
        } else {
            await api('/veiculos', { method: 'POST', body: JSON.stringify(payload) });
            toast('Veiculo cadastrado');
        }
        resetarFormVeiculo();
        carregarVeiculos();
        carregarResumo();
    } catch (err) { toast(err.message, 'erro'); }
});

$('#btn-cancelar').addEventListener('click', resetarFormVeiculo);

function resetarFormVeiculo() {
    $('#form-veiculo').reset();
    $('#veiculo-id').value = '';
    $('#btn-cancelar').classList.add('hidden');
}

async function editarVeiculo(id) {
    const v = await api('/veiculos/' + id);
    $('#veiculo-id').value = v.id;
    if (v.modelo && v.modelo.marca) {
        $('#filtro-marca-form').value = v.modelo.marca.id;
        await carregarModelos(v.modelo.marca.id, '#modelo-form');
    }
    $('#modelo-form').value = v.modelo.id;
    $('#ano').value = v.ano;
    $('#cor').value = v.cor;
    $('#preco').value = v.preco;
    $('#quilometragem').value = v.quilometragem;
    $('#status-form').value = v.status;
    $('#btn-cancelar').classList.remove('hidden');
    document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
    document.querySelector('.tab[data-tab="veiculo"]').classList.add('active');
    document.querySelectorAll('#form-modelo, #form-marca').forEach(f => f.classList.add('hidden'));
    $('#form-veiculo').classList.remove('hidden');
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

async function deletarVeiculo(id) {
    if (!confirm('Tem certeza que deseja remover este veiculo?')) return;
    try {
        await api('/veiculos/' + id, { method: 'DELETE' });
        toast('Veiculo removido');
        carregarVeiculos();
        carregarResumo();
    } catch (err) { toast(err.message, 'erro'); }
}

async function alterarStatus(id, novoStatus) {
    try {
        await api('/veiculos/' + id + '/status', {
            method: 'PATCH',
            body: JSON.stringify({ status: novoStatus })
        });
        toast('Status atualizado para ' + novoStatus.toLowerCase());
        carregarVeiculos();
        carregarResumo();
    } catch (err) { toast(err.message, 'erro'); }
}

function montarLinha(v) {
    const marca = v.modelo && v.modelo.marca ? v.modelo.marca.nome : '-';
    const modelo = v.modelo ? v.modelo.nome : '-';
    const tagClass = v.status.toLowerCase();
    return `
      <tr>
        <td>${v.id}</td>
        <td>${marca}</td>
        <td>${modelo}</td>
        <td>${v.ano}</td>
        <td>${v.cor}</td>
        <td>${formatKm(v.quilometragem)}</td>
        <td>${formatMoeda(v.preco)}</td>
        <td><span class="tag ${tagClass}">${v.status}</span></td>
        <td class="col-acoes">
          <div class="acoes-linha">
            <button class="btn primario pequeno" onclick="editarVeiculo(${v.id})">Editar</button>
            <button class="btn perigo pequeno" onclick="deletarVeiculo(${v.id})">Excluir</button>
            ${v.status !== 'VENDIDO' ? `<button class="btn secundario pequeno" onclick="alterarStatus(${v.id}, 'VENDIDO')">Vender</button>` : ''}
          </div>
        </td>
      </tr>`;
}

async function carregarVeiculos() {
    const params = new URLSearchParams();
    const adicionar = (k, sel) => { const v = $(sel).value; if (v) params.append(k, v); };
    adicionar('marcaId', '#filtro-marca');
    adicionar('modeloId', '#filtro-modelo');
    adicionar('status', '#filtro-status');
    adicionar('precoMin', '#filtro-preco-min');
    adicionar('precoMax', '#filtro-preco-max');
    adicionar('anoMin', '#filtro-ano-min');

    const query = params.toString() ? '?' + params.toString() : '';
    try {
        const lista = await api('/veiculos' + query);
        const tbody = $('#tabela-veiculos');
        tbody.innerHTML = lista.length === 0
            ? '<tr><td colspan="9" style="text-align:center;padding:24px;color:#9ca3af;">Nenhum veiculo encontrado</td></tr>'
            : lista.map(montarLinha).join('');
    } catch (err) { toast(err.message, 'erro'); }
}

async function carregarResumo() {
    try {
        const r = await api('/veiculos/resumo');
        $('#resumo-total').textContent = r.total;
        $('#resumo-disponivel').textContent = r.disponivel;
        $('#resumo-reservado').textContent = r.reservado;
        $('#resumo-vendido').textContent = r.vendido;
    } catch (err) { /* silencioso */ }
}

$('#btn-filtrar').addEventListener('click', carregarVeiculos);
$('#btn-limpar').addEventListener('click', () => {
    document.querySelectorAll('.filtros input, .filtros select').forEach(el => el.value = '');
    carregarVeiculos();
});

// ---------- INICIALIZACAO ----------
(async function init() {
    await carregarMarcas();
    await carregarModelos(null, '#filtro-modelo');
    await carregarModelos(null, '#modelo-form');
    await carregarVeiculos();
    await carregarResumo();
})();

window.editarVeiculo = editarVeiculo;
window.deletarVeiculo = deletarVeiculo;
window.alterarStatus = alterarStatus;
