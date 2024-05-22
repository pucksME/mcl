package at.tugraz.iti.mcl.project

class User (
    val firstName: String,
    val active: Boolean
) {

    fun activeToString(): String {
        return if (this.active) "Currently Active" else "Inactive"
    }
}