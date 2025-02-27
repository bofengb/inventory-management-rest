resource "random_password" "db_password" {
  length  = 4
  special = true
}
