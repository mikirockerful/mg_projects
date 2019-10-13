from django.contrib import admin

# Register your models here.
from .models import Question, Choice

# Heredar de los Inline permite editar los hijos desde la página de edición del padre (1:N)
class ChoiceInline(admin.TabularInline):
	model = Choice
	extra = 1

# Heredar de ModelAdmin permite modificar las propiedades de la interfaz de admin de un modelo
class QuestionAdmin(admin.ModelAdmin):
	# Fieldsets divide conjuntos de campos en secciones
	fieldsets = [
	(None,	             {'fields': ['question_text']}),
	('Date information', {'fields': ['pub_date']})
	]
	inlines = [ChoiceInline]
	list_display = ('question_text', 'pub_date', 'was_published_recently')
	# El tipo de filtro depende del tipo de campo. Para fechas, te marca el último día, semana, mes o año
	list_filter = ['pub_date']
	search_fields = ['question_text', 'choice__choice_text']

admin.site.register(Question, QuestionAdmin)
